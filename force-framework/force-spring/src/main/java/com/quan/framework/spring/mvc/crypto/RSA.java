package com.quan.framework.spring.mvc.crypto;

import java.lang.annotation.*;

/**
 * @author Force-oneself
 * @date 2023-08-21
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Decrypt
public @interface RSA {

}
