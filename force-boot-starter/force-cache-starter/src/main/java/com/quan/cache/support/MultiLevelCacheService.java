package com.quan.cache.support;

import com.quan.cache.core.CacheKey;
import com.quan.cache.core.CacheService;
import com.quan.cache.core.CacheValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 多级缓存实现
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public class MultiLevelCacheService implements CacheService {

    private static final Logger log = LoggerFactory.getLogger(MultiLevelCacheService.class);
    /**
     * 本地缓存
     */
    private final CacheService localCacheService;

    /**
     * 分布式缓存
     */
    private final CacheService remoteCacheService;

    /**
     * 分布式缓存是否处于降级状态
     */
    private final AtomicBoolean remoteFallback = new AtomicBoolean(false);

    public MultiLevelCacheService(CacheService localCacheService, CacheService remoteCacheService) {

        this.localCacheService = localCacheService;
        this.remoteCacheService = remoteCacheService;
        log.info("Multi-level cache service initialized");
    }

    @Override
    public <T> CacheValue<T> get(CacheKey key) {
        Assert.notNull(key, "Cache key must not be null");

        try {
            // 优先从本地缓存获取
            CacheValue<T> localValue = localCacheService.get(key);
            if (localValue != null && !localValue.isExpired()) {
                log.debug("Cache hit in local cache, key: {}", key.toKey());
                return localValue;
            }

            // Remote 正常且本地缓存未命中时，从 Remote 获取
            if (isRemoteHealthy()) {
                CacheValue<T> cacheValue = remoteCacheService.get(key);
                if (cacheValue != null && !cacheValue.isExpired()) {
                    log.debug("Cache hit in Remote cache, key: {}", key.toKey());
                    // 回写本地缓存
                    localCacheService.put(key, cacheValue);
                    return cacheValue;
                }
            } else {
                log.warn("Remote is unhealthy, using local cache only for key: {}", key.toKey());
            }

            return null;
        } catch (Exception e) {
            log.error("Error getting cache for key: {}", key.toKey(), e);
            // 发生异常时降级到本地缓存
            return localCacheService.get(key);
        }
    }

    @Override
    public <T> void put(CacheKey key, CacheValue<T> value) {
        Assert.notNull(key, "Cache key must not be null");

        try {
            // 同时写入本地缓存和Remote
            localCacheService.put(key, value);

            if (isRemoteHealthy()) {
                remoteCacheService.put(key, value);
            } else {
                log.warn("Remote is unhealthy, value only stored in local cache for key: {}", key.toKey());
            }
        } catch (Exception e) {
            log.error("Error putting cache for key: {}", key.toKey(), e);
            // 确保至少写入本地缓存
            localCacheService.put(key, value);
        }
    }

    @Override
    public void remove(CacheKey key) {
        Assert.notNull(key, "Cache key must not be null");

        try {
            // 同时从本地缓存和Remote删除
            localCacheService.remove(key);

            if (isRemoteHealthy()) {
                remoteCacheService.remove(key);
            } else {
                log.warn("Remote is unhealthy, value only removed from local cache for key: {}", key.toKey());
            }
        } catch (Exception e) {
            log.error("Error removing cache for key: {}", key.toKey(), e);
            // 确保至少从本地缓存删除
            localCacheService.remove(key);
        }
    }

    @Override
    public void clear() {
        try {
            // 同时清空本地缓存和Remote
            localCacheService.clear();

            if (isRemoteHealthy()) {
                remoteCacheService.clear();
            } else {
                log.warn("Remote is unhealthy, only local cache cleared");
            }
        } catch (Exception e) {
            log.error("Error clearing cache", e);
            // 确保至少清空本地缓存
            localCacheService.clear();
        }
    }

    @Override
    public boolean isExpired(CacheKey key) {
        // 本地有效即可
        return localCacheService.isExpired(key)
                // 远程缓存不可用或者远程缓存过期
                || (isHealthy() && remoteCacheService.isExpired(key));
    }

    @Override
    public void setExpiration(CacheKey key) {
        localCacheService.setExpiration(key);
        if (isHealthy()) {
            remoteCacheService.setExpiration(key);
        }
    }

    @Override
    public long getExpiration(CacheKey key) {
        long remoteExpire = isHealthy() ? remoteCacheService.getExpiration(key) : -1L;
        return Math.max(localCacheService.getExpiration(key), remoteExpire);
    }

    @Override
    public boolean isHealthy() {
        // 只要本地缓存正常就认为服务是可用的
        return localCacheService.isHealthy();
    }

    @Override
    public void destroy() throws Exception {
        log.info("Shutting down multi-level cache service...");
        try {
            localCacheService.destroy();
            if (isRemoteHealthy()) {
                remoteCacheService.destroy();
            }
        } catch (Exception e) {
            log.error("Error during cache service shutdown", e);
            throw e;
        }
    }

    /**
     * 检查Remote是否健康
     */
    private boolean isRemoteHealthy() {
        boolean remoteHealthy = remoteCacheService.isHealthy();
        // 状态发生变化时记录日志
        if (remoteFallback.compareAndSet(!remoteHealthy, remoteHealthy)) {
            if (remoteHealthy) {
                log.info("Remote recovered, switching back to multi-level cache mode");
            } else {
                log.warn("Remote is down, falling back to local cache only mode");
            }
        }
        return remoteHealthy;
    }

}