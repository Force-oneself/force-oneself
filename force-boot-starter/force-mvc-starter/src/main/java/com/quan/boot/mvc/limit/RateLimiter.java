package com.quan.boot.mvc.limit;

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
     * @return 是否通过
     */
    boolean rateLimit();
}
