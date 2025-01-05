package com.quan.cache.support.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.quan.cache.support.local.cleanup.CacheCleanupStrategy;
import com.quan.cache.support.local.cleanup.IdleCleanupStrategy;
import com.quan.cache.support.local.cleanup.RatioCleanupStrategy;
import com.quan.cache.support.local.cleanup.ScheduledCleanupStrategy;
import com.quan.cache.properties.LocalCacheProperties;
import com.quan.cache.core.CacheKey;
import com.quan.cache.core.CacheService;
import com.quan.cache.core.CacheValue;
import com.quan.cache.support.local.policy.CleanupPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;


/**
 * 基于 Caffeine 的本地缓存实现
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public class CaffeineLocalCacheService implements CacheService {

    private static final Logger log = LoggerFactory.getLogger(CaffeineLocalCacheService.class);

    private final Cache<String, CacheValue<?>> cache;
    private final CacheCleanupStrategy cleanupStrategy;
    private final LocalCacheProperties properties;

    public CaffeineLocalCacheService(LocalCacheProperties properties) {
        Assert.notNull(properties, "Properties must not be null");
        this.properties = properties;
        this.cache = buildCache();
        this.cleanupStrategy = createCleanupStrategy();
        this.cleanupStrategy.start();
        log.info("Local cache initialized with properties: {}", properties);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> CacheValue<T> get(CacheKey key) {
        try {
            Assert.notNull(key, "Cache key must not be null");
            CacheValue<?> value = cache.getIfPresent(key.toKey());
            if (value == null) {
                return null;
            }
            // 检查是否过期
            if (value.isExpired()) {
                cache.invalidate(key.toKey());
                return null;
            }
            return (CacheValue<T>) value;
        } catch (Exception e) {
            log.error("Failed to get cache for key: {}", key.toKey(), e);
            return null;
        }
    }

    @Override
    public <T> void put(CacheKey key, CacheValue<T> value) {
        try {
            Assert.notNull(key, "Cache key must not be null");
            if (value == null || value.getValue() == null) {
                return;
            }
            cache.put(key.toKey(), value);

            // 如果是写入时清理策略，则在写入时触发清理
            if (properties.getCleanup().getPolicy() == CleanupPolicy.WRITE) {
                cleanupStrategy.cleanup(cache);
            }
        } catch (Exception e) {
            log.error("Failed to put cache for key: {}", key.toKey(), e);
        }
    }

    @Override
    public void remove(CacheKey key) {
        try {
            Assert.notNull(key, "Cache key must not be null");
            cache.invalidate(key.toKey());
        } catch (Exception e) {
            log.error("Failed to remove cache for key: {}", key.toKey(), e);
        }
    }

    @Override
    public void clear() {
        try {
            cache.invalidateAll();
            log.info("Cache cleared");
        } catch (Exception e) {
            log.error("Failed to clear cache", e);
        }
    }

    @Override
    public boolean isExpired(CacheKey key) {
        // Caffeine 不直接支持过期检查，需通过其他方式实现
        // 这里可以根据需要实现具体的过期逻辑
        CacheValue<Object> cacheValue = this.get(key);
        return cacheValue != null && cacheValue.isExpired();
    }

    @Override
    public synchronized void setExpiration(CacheKey key) {
        CacheValue<Object> cacheValue = this.get(key);
        if (cacheValue!= null) {
            // 设置过期时间
            this.remove(key);
            cacheValue.withExpireTime(key.timeUnit().toMillis(key.timeout()) + System.currentTimeMillis());
            this.put(key, cacheValue);
        }
    }


    @Override
    public long getExpiration(CacheKey key) {
        // Caffeine 不提供直接获取剩余过期时间的方法
        // 这里可以根据需要实现具体的过期逻辑
        CacheValue<Object> cacheValue = this.get(key);
        // 返回0表示永不过期, 负数代表异常
        return cacheValue != null ? cacheValue.getExpireTime() : -1;
    }

    @Override
    public boolean isHealthy() {
        return true;
    }

    @Override
    public void destroy() {
        log.info("Shutting down local cache service...");
        try {
            cleanupStrategy.stop();
            cache.invalidateAll();
            cache.cleanUp();
            log.info("Local cache service shutdown completed");
        } catch (Exception e) {
            log.error("Error during cache service shutdown", e);
        }
    }

    /**
     * 构建缓存实例
     */
    private Cache<String, CacheValue<?>> buildCache() {
        // 构建缓存
        Caffeine<String, CacheValue<?>> builder = Caffeine.newBuilder()
                .initialCapacity(properties.getInitialCapacity())
                .expireAfterWrite(properties.getExpireAfterMinutes(), TimeUnit.MINUTES)
                .removalListener((String key, CacheValue<?> value, RemovalCause cause) -> {
                    if (cause.wasEvicted()) {
                        log.debug("Cache entry evicted, key: {}, cause: {}, policy: {}",
                                key, cause, properties.getEvictionPolicy());
                    }
                })
                .recordStats();

        // 根据不同的淘汰策略配置缓存
        switch (properties.getEvictionPolicy()) {
            case LRU:
                // Caffeine 默认使用 Window TinyLFU，这是一种改进的 LRU 算法
                builder.maximumSize(properties.getMaximumSize());
                break;

            case LFU:
                // 使用 Frequency Sketch 记录访问频率
                builder.maximumSize(properties.getMaximumSize())
                        .weigher((key, value) -> 1);
                break;

            case FIFO:
                // 使用写入时间作为权重
                builder.maximumSize(properties.getMaximumSize())
                        .weigher((key, value) -> 1)
                        .expireAfterWrite(properties.getExpireAfterMinutes(), TimeUnit.MINUTES);
                break;

            case RANDOM:
                // 随机淘汰通过自定义 Weigher 实现
                builder.maximumSize(properties.getMaximumSize())
                        .weigher((key, value) ->
                                (int) (Math.random() * 100));
                break;

            default:
                builder.maximumSize(properties.getMaximumSize());
        }

        return builder.build();
    }

    /**
     * 创建清理策略
     */
    private CacheCleanupStrategy createCleanupStrategy() {
        switch (properties.getCleanup().getPolicy()) {
            case RATIO:
            case WRITE:
                return new RatioCleanupStrategy(
                        cache,
                        properties.getCleanup().getCleanupRatio(),
                        properties.getCleanup().getBatchSize(),
                        properties.getMaximumSize()
                );
            case IDLE:
                return new IdleCleanupStrategy(
                        cache,
                        properties.getCleanup().getIdleSeconds(),
                        properties.getCleanup().getBatchSize()
                );
            default:
                return new ScheduledCleanupStrategy(
                        cache,
                        properties.getCleanup().getIntervalSeconds()
                );
        }
    }

}