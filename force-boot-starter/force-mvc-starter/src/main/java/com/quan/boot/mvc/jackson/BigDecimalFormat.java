package com.quan.boot.mvc.jackson;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * BigDecimalFormat
 *
 * @author Force-oneself
 * @date 2023-01-31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = BigDecimalSerializer.class)
@JsonDeserialize(using = BigDecimalDeserializer.class)
public @interface BigDecimalFormat {

    /**
     * 默认值, 凡是加了 @BigDecimalFormat 注解, 又没有指定 value 值的, 都会被格式化为下面的形式
     */
    String value() default "#0.00";
}
