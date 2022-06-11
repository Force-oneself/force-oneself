package com.quan.framework.spring.valid.annotation;


import com.quan.framework.spring.valid.impl.PasswordValidator;
import com.quan.framework.spring.valid.type.PasswordType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author chenjipdc@gmail.com
 * @date 2021/1/12 9:45 上午
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    /**
     * 密码最小长度
     */
    int minLength() default 8;

    /**
     * 密码最大长度
     */
    int maxLength() default 16;

    /**
     * 密码复杂度
     *
     */
    PasswordType passwordType() default PasswordType.MEDIUM;

    //校验失败的默认信息
    String message() default "密码格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
