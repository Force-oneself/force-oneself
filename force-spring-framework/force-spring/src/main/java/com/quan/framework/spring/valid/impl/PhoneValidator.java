package com.quan.framework.spring.valid.impl;

import com.quan.framework.spring.valid.annotation.Phone;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 手机号码校验
 *
 * @author chenjipdc@gmail.com
 * @date 2020/12/28 2:23 下午
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final String PHONE_PATTERN_STRING = "^[1][\\d]{10}$";

    private static final Pattern PATTERN = Pattern.compile(PHONE_PATTERN_STRING);

    private Phone phone;

    @Override
    public void initialize(Phone constraintAnnotation) {
        this.phone = constraintAnnotation;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return PATTERN.matcher(s)
                .matches();
    }
}
