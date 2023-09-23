package com.quan.boot.mvc.desensitization;

import java.lang.annotation.*;

/**
 * Desensitization
 *
 * @author Force-oneself
 * @date 2022-07-03
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Desensitization {

    /**
     * 脱敏类型
     * @see DesensitizationType
     * @return /
     */
    String type();

    /**
     * 是否内置类型
     *
     * @return /
     */
    boolean inner() default true;

}
