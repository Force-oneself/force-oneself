package com.quan.cache.support.local.cleanup;

import com.github.benmanes.caffeine.cache.Cache;
import com.quan.cache.core.CacheValue;
import com.quan.cache.support.local.TimedCacheValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 空闲时间清理策略
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public class IdleCleanupStrategy implements CacheCleanupStrategy {

    private static final Logger log = LoggerFactory.getLogger(IdleCleanupStrategy.class);

    private final ScheduledExecutorService executor;
    private final int idleSeconds;
    private final int batchSize;
    private final Cache<String, CacheValue<?>> cache;

    public IdleCleanupStrategy(Cache<String, CacheValue<?>> cache, 
                              int idleSeconds, 
                              int batchSize) {
        this.cache = cache;
        this.idleSeconds = idleSeconds;
        this.batchSize = batchSize;
        this.executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread thread = new Thread(r, "cache-idle-cleanup");
            thread.setDaemon(true);
            return thread;
        });
    }

    @Override
    public int cleanup(Cache<String, CacheValue<?>> cache) {
        int count = 0;
        long idleThreshold = System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(idleSeconds);

        for (String key : cache.asMap().keySet()) {
            if (count >= batchSize) {
                break;
            }
            
            CacheValue<?> value = cache.getIfPresent(key);
            if (value instanceof TimedCacheValue) {
                TimedCacheValue<?> timedValue = (TimedCacheValue<?>) value;
                if (timedValue.getLastAccessTime() < idleThreshold) {
                    cache.invalidate(key);
                    count++;
                }
            }
        }
        
        log.debug("Idle cleanup completed, removed {} entries", count);
        return count;
    }

    @Override
    public void start() {
        executor.scheduleWithFixedDelay(() -> {
            try {
                cleanup(cache);
            } catch (Exception e) {
                log.error("Error during idle cleanup", e);
            }
        }, idleSeconds, idleSeconds, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
} 