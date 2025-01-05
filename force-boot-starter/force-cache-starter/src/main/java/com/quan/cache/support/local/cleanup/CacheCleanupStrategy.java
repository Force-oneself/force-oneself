package com.quan.cache.support.local.cleanup;

import com.github.benmanes.caffeine.cache.Cache;
import com.quan.cache.core.CacheValue;

/**
 * 缓存清理策略接口
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public interface CacheCleanupStrategy {
    
    /**
     * 执行清理
     *
     * @param cache 缓存实例
     * @return 清理的条目数
     */
    int cleanup(Cache<String, CacheValue<?>> cache);
    
    /**
     * 启动清理任务
     */
    void start();
    
    /**
     * 停止清理任务
     */
    void stop();
} 