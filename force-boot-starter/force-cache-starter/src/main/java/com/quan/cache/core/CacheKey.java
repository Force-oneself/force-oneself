package com.quan.cache.core;

import java.util.concurrent.TimeUnit;

/**
 * 缓存键接口
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public interface CacheKey {
    
    /**
     * 默认过期时间 0，表示永不过期
     */
    long DEFAULT_TIMEOUT = 0L;

    /**
     * 默认时间单位为秒
     */
    TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 获取缓存的过期时间
     * 
     * @return 过期时间值，0表示永不过期
     * @see #DEFAULT_TIMEOUT
     */
    default long timeout() {
        return DEFAULT_TIMEOUT;
    }

    /**
     * 获取过期时间的时间单位
     * 
     * @return 时间单位，默认为秒
     * @see TimeUnit
     * @see #DEFAULT_TIME_UNIT
     */
    default TimeUnit timeUnit() {
        return DEFAULT_TIME_UNIT;
    }

    /**
     * 判断缓存是否永不过期
     * 
     * @return true 表示永不过期，false 表示会过期
     * @see #timeout()
     */
    default boolean neverExpire() {
        return timeout() == 0;
    }

    /**
     * 生成缓存的唯一标识键
     * <p>
     * 该方法用于生成在缓存系统中唯一标识当前缓存项的键。
     * 实现类需要确保生成的键在整个缓存系统中是唯一的。
     *
     * @return 缓存的唯一标识键
     */
    String toKey();

}