package com.quan.demo.limit.local;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 令牌桶限流器
 *
 * @author Force-oneself
 * @date 2023-01-08
 */
@SuppressWarnings(value = "all")
public class TokenBucketRateLimiter implements com.quan.demo.limit.RateLimiter {

    private final RateLimiter rateLimiter;

    public TokenBucketRateLimiter(int capacity) {
        this.rateLimiter = RateLimiter.create(capacity);
    }

    @Override
    public boolean rateLimit() {
        return rateLimiter.tryAcquire();
    }
}
