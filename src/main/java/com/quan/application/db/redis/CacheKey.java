package com.quan.application.db.redis;

import org.springframework.lang.Nullable;

import java.time.Duration;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-04-24
 **/
public class CacheKey {
    /**
     * redis key
     */
    private final String key;
    /**
     * 超时时间 秒
     */
    @Nullable
    private Duration expire;

    public CacheKey(String key, @Nullable Duration expire) {
        this.key = key;
        this.expire = expire;
    }

    public String getKey() {
        return key;
    }

    @Nullable
    public Duration getExpire() {
        return expire;
    }

    public void setExpire(@Nullable Duration expire) {
        this.expire = expire;
    }

}
