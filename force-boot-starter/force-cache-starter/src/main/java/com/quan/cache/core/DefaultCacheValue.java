package com.quan.cache.core;

/**
 * 默认缓存值实现
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public class DefaultCacheValue<T> implements CacheValue<T> {
    
    /**
     * 缓存值
     */
    private final T value;
    
    /**
     * 过期时间戳（毫秒）
     */
    private final long expireTime;
    
    /**
     * 创建时间戳（毫秒）
     */
    private final long createTime;

    public DefaultCacheValue(T value, long expireTime) {
        this.value = value;
        this.expireTime = expireTime;
        this.createTime = System.currentTimeMillis();
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public long getExpireTime() {
        return expireTime;
    }

    @Override
    public long getCreateTime() {
        return createTime;
    }

    @Override
    public boolean isExpired() {
        return expireTime > 0 && System.currentTimeMillis() > expireTime;
    }

    @Override
    public CacheValue<T> withExpireTime(long expireTime) {
        return new DefaultCacheValue<>(this.value, expireTime);
    }

    /**
     * 创建指定TTL的缓存值
     *
     * @param value 缓存对象
     * @param ttlMillis 存活时间（毫秒）
     * @return 缓存值对象
     */
    public static <T> DefaultCacheValue<T> createWithTTL(T value, long ttlMillis) {
        return new DefaultCacheValue<>(value, 
            ttlMillis > 0 ? System.currentTimeMillis() + ttlMillis : 0);
    }

    /**
     * 创建永不过期的缓存值
     *
     * @param value 缓存对象
     * @return 缓存值对象
     */
    public static <T> DefaultCacheValue<T> createNeverExpire(T value) {
        return new DefaultCacheValue<>(value, 0);
    }
} 