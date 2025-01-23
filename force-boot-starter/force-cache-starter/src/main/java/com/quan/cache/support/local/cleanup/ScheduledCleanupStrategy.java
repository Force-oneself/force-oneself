package com.quan.cache.support.local.cleanup;

import com.github.benmanes.caffeine.cache.Cache;
import com.quan.cache.core.CacheValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时清理策略
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public class ScheduledCleanupStrategy implements CacheCleanupStrategy {

    private static final Logger log = LoggerFactory.getLogger(ScheduledCleanupStrategy.class);

    private final ScheduledExecutorService executor;
    private final int intervalSeconds;
    private final Cache<String, CacheValue<?>> cache;

    public ScheduledCleanupStrategy(Cache<String, CacheValue<?>> cache, int intervalSeconds) {
        this.cache = cache;
        this.intervalSeconds = intervalSeconds;
        this.executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread thread = new Thread(r, "cache-scheduled-cleanup");
            thread.setDaemon(true);
            return thread;
        });
    }

    @Override
    public int cleanup(Cache<String, CacheValue<?>> cache) {
        int[] count = {0};
        cache.asMap().forEach((key, value) -> {
            if (value != null && value.isExpired()) {
                cache.invalidate(key);
                count[0]++;
            }
        });
        return count[0];
    }

    @Override
    public void start() {
        executor.scheduleAtFixedRate(() -> {
            try {
                log.debug("Starting scheduled cleanup");
                int cleaned = cleanup(cache);
                log.debug("Scheduled cleanup completed, removed {} entries", cleaned);
            } catch (Exception e) {
                log.error("Error during scheduled cleanup", e);
            }
        }, intervalSeconds, intervalSeconds, TimeUnit.SECONDS);
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