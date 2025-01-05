package com.quan.cache.support.local.policy;

/**
 * 缓存清理策略
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public enum CleanupPolicy {
    /**
     * 定时清理
     */
    SCHEDULED,
    
    /**
     * 按比例清理
     */
    RATIO,
    
    /**
     * 空闲时清理
     */
    IDLE,
    
    /**
     * 写入时清理
     */
    WRITE
} 