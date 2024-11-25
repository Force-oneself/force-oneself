package com.quan.boot.mvc.jackson.auto;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.*;

/**
 * AutoFill
 *
 * @author Force-oneself
 * @date 2023-01-31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = AutoFillSerializer.class)
public @interface AutoFill {

    String value() default "auto";
}
