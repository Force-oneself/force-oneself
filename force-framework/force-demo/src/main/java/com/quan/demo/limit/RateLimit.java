package com.quan.demo.limit;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 限流注解
 *
 * @author Force-oneself
 * @date 2023-01-09
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RateLimit {

    /**
     * 限流的唯一标识
     *
     * @return 唯一标识key
     */
    String key() default "";

    /**
     * 限流时间单位
     *
     * @return TimeUnit
     */
    TimeUnit unit() default TimeUnit.SECONDS;

    /**
     * 限流时间
     *
     * @return 时间
     */
    long duration() default 1;

    /**
     * 单位时间内允许通过的数量
     *
     * @return 流量
     */
    long flow() default 100;

}
