package com.quan.cache.core;

/**
 * 缓存值接口
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public interface CacheValue<T> {

    /**
     * 获取缓存对象
     *
     * @return 缓存对象
     */
    T getValue();

    /**
     * 获取过期时间戳（毫秒）
     *
     * @return 过期时间戳
     */
    long getExpireTime();

    /**
     * 获取创建时间戳（毫秒）
     *
     * @return 创建时间戳
     */
    long getCreateTime();

    /**
     * 检查缓存是否过期
     *
     * @return true 表示已过期，false 表示未过期
     */
    boolean isExpired();

    /**
     * 设置新的过期时间
     *
     * @param expireTime 新的过期时间戳（毫秒）
     * @return 更新后的缓存值对象
     */
    CacheValue<T> withExpireTime(long expireTime);
} 