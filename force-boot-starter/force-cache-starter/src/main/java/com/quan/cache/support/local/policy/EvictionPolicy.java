package com.quan.cache.support.local.policy;

/**
 * 缓存淘汰策略
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public enum EvictionPolicy {
    /**
     * 最近最少使用
     */
    LRU,
    
    /**
     * 最不经常使用
     */
    LFU,
    
    /**
     * 先进先出
     */
    FIFO,
    
    /**
     * 随机淘汰
     */
    RANDOM
} 