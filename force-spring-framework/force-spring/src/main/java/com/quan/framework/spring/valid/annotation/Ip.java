package com.quan.framework.spring.valid.annotation;


import com.quan.framework.spring.valid.impl.IpValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 校验ip注解
 * @author chenjipdc@gmail.com
 * @date 2020/12/29 2:19 下午
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IpValidator.class)
public @interface Ip {
    //校验错误的默认信息
    String message() default "请输入正确的ip";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
