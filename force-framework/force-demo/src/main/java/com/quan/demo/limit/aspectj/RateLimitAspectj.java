package com.quan.demo.limit.aspectj;

import com.quan.demo.limit.RateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * RateLimitAspactj
 *
 * @author Force-oneself
 * @date 2023-01-11
 */
@Component
@Aspect
public class RateLimitAspectj {


    @Around("@annotation(limiter)")
    public Object aroundRateLimiter(ProceedingJoinPoint point, RateLimit limiter) throws Throwable {

        return point.proceed();
    }
}
