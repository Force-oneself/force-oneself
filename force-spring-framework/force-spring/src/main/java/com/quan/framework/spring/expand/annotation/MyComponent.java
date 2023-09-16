package com.quan.framework.spring.expand.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Force-oneself
 * @Description MyComponent.java
 * @date 2021-07-14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyComponent {
}
