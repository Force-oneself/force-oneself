package com.quan.framework.spring.valid.impl;


import cn.hutool.core.net.Ipv4Util;
import com.quan.framework.spring.valid.annotation.Ip;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * ip校验器，使用注解统一校验，减少重复校验代码，解耦。
 *
 * @author chenjipdc@gmail.com
 * @date 2020/12/29 2:20 下午
 */
public class IpValidator implements ConstraintValidator<Ip, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Ipv4Util.isMaskValid(value);
    }

}
