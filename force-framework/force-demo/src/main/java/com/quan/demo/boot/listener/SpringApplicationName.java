package com.quan.demo.boot.listener;

import java.lang.annotation.*;

/**
 * @author Force-oneself
 * @date 2023-04-12
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SpringApplicationName {

    String name() default "application-name";

    String prefix() default "";
}
