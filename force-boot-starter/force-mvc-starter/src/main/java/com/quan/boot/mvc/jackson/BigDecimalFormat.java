package com.quan.boot.mvc.jackson;

import java.lang.annotation.*;

/**
 * BigDecimalFormat
 *
 * @author Force-oneself
 * @date 2023-01-31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface BigDecimalFormat {

    /**
     * 默认值, 凡是加了 @BigDecimalFormat 注解, 又没有指定 value 值的, 都会被格式化为下面的形式
     */
    String value() default "#0.00";
}
