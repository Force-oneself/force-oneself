package com.quan.framework.spring.valid.impl;

import com.quan.framework.spring.valid.annotation.Password;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author chenjipdc@gmail.com
 * @date 2021/1/12 9:46 上午
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private Password password;

    @Override
    public void initialize(Password constraintAnnotation) {
        this.password = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("密码不能为空")
                    .addConstraintViolation();
            return false;
        }

        if (!(password.minLength() <= value.length() && password.maxLength() >= value.length())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("密码长度为" + password.minLength() + "-" + password.maxLength() + "位")
                    .addConstraintViolation();
            return false;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("密码应" + password.passwordType()
                .getDesc())
                .addConstraintViolation();
        return false;
    }
}
