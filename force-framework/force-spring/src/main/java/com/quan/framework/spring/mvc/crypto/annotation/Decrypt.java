package com.quan.framework.spring.mvc.crypto.annotation;

import java.lang.annotation.*;

/**
 * @author Force-oneself
 * @date 2023-03-03
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {

    String name() default "default";

    /**
     * 使用的算法
     * @return 算法名
     */
    String algorithm() default "default";
}
