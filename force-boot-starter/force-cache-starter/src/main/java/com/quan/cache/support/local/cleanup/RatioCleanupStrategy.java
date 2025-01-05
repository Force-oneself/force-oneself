package com.quan.cache.support.local.cleanup;

import com.github.benmanes.caffeine.cache.Cache;
import com.quan.cache.core.CacheValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 按比例清理策略
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public class RatioCleanupStrategy implements CacheCleanupStrategy {

    private static final Logger log = LoggerFactory.getLogger(RatioCleanupStrategy.class);

    private final double cleanupRatio;
    private final int batchSize;
    private final int maximumSize;
    private final Cache<String, CacheValue<?>> cache;

    public RatioCleanupStrategy(Cache<String, CacheValue<?>> cache, 
                               double cleanupRatio, 
                               int batchSize, 
                               int maximumSize) {
        this.cache = cache;
        this.cleanupRatio = cleanupRatio;
        this.batchSize = batchSize;
        this.maximumSize = maximumSize;
    }

    @Override
    public int cleanup(Cache<String, CacheValue<?>> cache) {
        int currentSize = cache.asMap().size();
        if (currentSize < maximumSize * cleanupRatio) {
            return 0;
        }

        int count = 0;
        int target = Math.min(batchSize, 
            (int) ((currentSize - maximumSize * (1 - cleanupRatio))));

        for (String key : cache.asMap().keySet()) {
            if (count >= target) {
                break;
            }
            CacheValue<?> value = cache.getIfPresent(key);
            if (value != null && value.isExpired()) {
                cache.invalidate(key);
                count++;
            }
        }
        
        log.debug("Ratio cleanup completed, removed {} entries", count);
        return count;
    }

    @Override
    public void start() {
        // 无需启动，在写入时触发
    }

    @Override
    public void stop() {
        // 无需停止
    }
} 