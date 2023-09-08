package com.quan.boot.mvc.crypto.annotation;

import java.lang.annotation.*;

/**
 * @author Force-oneself
 * @date 2023-03-03
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Encrypt {

}
