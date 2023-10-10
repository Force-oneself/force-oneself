package com.quan.boot.mvc.limit;

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
     * 单位时间(单位：ms)
     * @return 限流单位时间
     */
    long time() default 1000;

    /**
     * 单位时间内允许通过的数量
     *
     * @return 流量
     */
    long capacity() default 100;

}
