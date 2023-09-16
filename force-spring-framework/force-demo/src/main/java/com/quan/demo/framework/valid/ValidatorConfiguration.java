package com.quan.demo.framework.valid;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * ValidatorConfiguration
 *
 * @author Force-oneself
 * @date 2022-12-24
 */
@Configuration
public class ValidatorConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public Validator validator(AutowireCapableBeanFactory beanFactory) {
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .constraintValidatorFactory(new SpringConstraintValidatorFactory(beanFactory))
                .buildValidatorFactory()
                .getValidator();
    }
}
