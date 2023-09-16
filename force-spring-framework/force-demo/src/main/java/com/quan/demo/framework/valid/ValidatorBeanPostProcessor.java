package com.quan.demo.framework.valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.validation.Validator;


/**
 * ValidatorBeanPostProcessor
 *
 * @author Force-oneself
 * @date 2022-12-24
 */
@Component
public class ValidatorBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof Validator)) {
            return bean;
        }
        Validator validator = (Validator) bean;
        System.out.println(validator);
        return validator;
    }
}
