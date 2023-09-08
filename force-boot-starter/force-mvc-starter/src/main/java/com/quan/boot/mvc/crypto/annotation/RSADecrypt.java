package com.quan.boot.mvc.crypto.annotation;

import java.lang.annotation.*;

/**
 * @author Force-oneself
 * @date 2023-08-21
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Decrypt
public @interface RSADecrypt {

}
