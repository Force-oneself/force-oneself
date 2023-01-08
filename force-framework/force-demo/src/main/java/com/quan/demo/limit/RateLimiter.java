package com.quan.demo.limit;

/**
 * 限流器
 *
 * @author Force-oneself
 * @date 2023-01-07
 */
@FunctionalInterface
public interface RateLimiter {

    /**
     * 限流控制
     *
     * @return 是否被限制
     */
    boolean rateLimit();
}
