package com.quan.cache.core;

import java.util.StringJoiner;

/**
 * 默认缓存键实现
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public class DefaultCacheKey<T> implements CacheKey {
    
    /**
     * 业务键
     */
    private final String businessKey;
    
    /**
     * 缓存类型
     */
    private final Class<T> type;
    
    /**
     * 服务模块名称（内部控制）
     */
    private static final String MODULE = "force-cache";
    
    /**
     * 版本号（内部控制）
     */
    private static final String VERSION = "1.0";

    public DefaultCacheKey(String businessKey, Class<T> type) {
        this.businessKey = businessKey;
        this.type = type;
    }

    @Override
    public String toKey() {
        return new StringJoiner(":")
                .add(MODULE)
                .add(VERSION)
                .add(type.getSimpleName())
                .add(businessKey)
                .toString();
    }

}