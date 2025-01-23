package com.quan.cache.support.redis;

import com.quan.cache.core.CacheKey;
import com.quan.cache.core.CacheService;
import com.quan.cache.core.CacheValue;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Redisson实现的缓存服务
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public class RedissonCacheService implements CacheService {

    private static final Logger log = LoggerFactory.getLogger(RedissonCacheService.class);

    /**
     * 健康检查间隔（毫秒）
     */
    private static final long HEALTH_CHECK_INTERVAL = 30000L;

    private final RedissonClient redissonClient;
    private final AtomicBoolean healthy = new AtomicBoolean(true);
    private final ScheduledExecutorService healthCheckExecutor;
    private final ScheduledFuture<?> healthCheckFuture;

    public RedissonCacheService(RedissonClient redissonClient) {
        Assert.notNull(redissonClient, "RedissonClient must not be null");
        this.redissonClient = redissonClient;
        this.healthCheckExecutor = createHealthCheckExecutor();
        this.healthCheckFuture = startHealthCheck();
    }

    @Override
    public <T> CacheValue<T> get(CacheKey key) {
        try {
            RBucket<CacheValue<T>> bucket = redissonClient.getBucket(key.toKey());
            return bucket.get();
        } catch (Exception e) {
            log.error("Failed to get cache for key: {}", key.toKey(), e);
            return null;
        }
    }

    @Override
    public <T> void put(CacheKey key, CacheValue<T> value) {
        try {
            if (value == null || value.getValue() == null) {
                return;
            }
            RBucket<CacheValue<T>> bucket = redissonClient.getBucket(key.toKey());
            if (key.neverExpire()) {
                bucket.set(value);
            } else {
                bucket.set(value, Duration.ofMillis(key.timeUnit().toMillis(key.timeout())));
            }
        } catch (Exception e) {
            log.error("Failed to put cache for key: {}", key.toKey(), e);
        }
    }

    @Override
    public void remove(CacheKey key) {
        try {
            redissonClient.getBucket(key.toKey()).delete();
        } catch (Exception e) {
            log.error("Failed to remove cache for key: {}", key.toKey(), e);
        }
    }

    @Override
    public void clear() {
        try {
            redissonClient.getKeys().flushdb();
        } catch (Exception e) {
            log.error("Failed to clear cache", e);
        }
    }

    @Override
    public boolean isExpired(CacheKey key) {
        long expiration = getExpiration(key);
        return expiration <= 0;
    }

    @Override
    public void setExpiration(CacheKey key) {
        try {
            RBucket<Object> bucket = redissonClient.getBucket(key.toKey());
            bucket.expire(Duration.ofMillis(key.timeUnit().toMillis(key.timeout())));
        } catch (Exception e) {
            log.error("Failed to set expiration for key: {}", key.toKey(), e);
        }
    }

    @Override
    public long getExpiration(CacheKey key) {
        try {
            RBucket<Object> bucket = redissonClient.getBucket(key.toKey());
            return bucket.remainTimeToLive();
        } catch (Exception e) {
            log.error("Failed to get expiration for key: {}", key.toKey(), e);
            // 表示获取失败
            return -1;
        }
    }

    @Override
    public boolean isHealthy() {
        return healthy.get();
    }

    @Override
    public void destroy() {
        log.info("Shutting down cache service health check...");
        try {
            // 取消定时任务
            if (healthCheckFuture != null && !healthCheckFuture.isCancelled()) {
                healthCheckFuture.cancel(true);
            }

            // 关闭线程池
            if (healthCheckExecutor != null && !healthCheckExecutor.isShutdown()) {
                healthCheckExecutor.shutdown();
                try {
                    // 等待线程池关闭，最多等待5秒
                    if (!healthCheckExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                        healthCheckExecutor.shutdownNow();
                        // 再次等待，确保任务被终止
                        if (!healthCheckExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                            log.warn("Health check executor did not terminate");
                        }
                    }
                } catch (InterruptedException e) {
                    healthCheckExecutor.shutdownNow();
                    Thread.currentThread().interrupt();
                }
            }
        } finally {
            healthy.set(false);
            log.info("Cache service health check shutdown completed");
        }
    }

    /**
     * 执行健康检查
     */
    private void checkHealth() {
        try {
            // 方法1：使用PING命令
            // boolean pingResult = redissonClient.getNodesGroup().pingAll();
            // if (!pingResult) {
            //     healthy.set(false);
            //     log.warn("Redis ping failed");
            //     return;
            // }

            // 方法2：尝试写入测试数据
            RBucket<String> healthCheck = redissonClient.getBucket("HEALTH_CHECK_KEY", StringCodec.INSTANCE);
            String value = String.valueOf(System.currentTimeMillis());
            healthCheck.set(value, 1, TimeUnit.MINUTES);
            String retrieved = healthCheck.get();

            if (!value.equals(retrieved)) {
                healthy.set(false);
                log.warn("Redis health check failed: write/read mismatch");
                return;
            }

            // 方法3：检查连接状态
            // if (!redissonClient.getNodesGroup().getNodes().stream()
            //         // Ping 5s
            //         .allMatch(node -> node.ping(5, TimeUnit.SECONDS))) {
            //     healthy.set(false);
            //     log.warn("Redis nodes check failed: some nodes are disconnected or have failed slaves");
            //     return;
            // }

            // 所有检查都通过
            healthy.set(true);
            log.debug("Redis health check passed");

        } catch (Exception e) {
            healthy.set(false);
            log.error("Redis health check failed with error", e);
        }
    }

    /**
     * 创建健康检查线程池
     */
    private ScheduledExecutorService createHealthCheckExecutor() {
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger threadNumber = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "cache-health-check-" + threadNumber.getAndIncrement());
                thread.setDaemon(true);
                return thread;
            }
        };
        return new ScheduledThreadPoolExecutor(1, threadFactory);
    }

    /**
     * 启动健康检查
     */
    private ScheduledFuture<?> startHealthCheck() {
        return healthCheckExecutor.scheduleWithFixedDelay(
                this::checkHealth,
                0,
                HEALTH_CHECK_INTERVAL,
                TimeUnit.MILLISECONDS
        );
    }
} 