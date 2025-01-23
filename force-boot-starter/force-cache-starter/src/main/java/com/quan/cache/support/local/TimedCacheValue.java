package com.quan.cache.support.local;

import com.quan.cache.core.DefaultCacheValue;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 带时间戳的缓存值实现
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public class TimedCacheValue<T> extends DefaultCacheValue<T> {

    private final AtomicLong lastAccessTime;

    public TimedCacheValue(T value, long expireTime) {
        super(value, expireTime);
        this.lastAccessTime = new AtomicLong(System.currentTimeMillis());
    }

    @Override
    public T getValue() {
        lastAccessTime.set(System.currentTimeMillis());
        return super.getValue();
    }

    public long getLastAccessTime() {
        return lastAccessTime.get();
    }
}