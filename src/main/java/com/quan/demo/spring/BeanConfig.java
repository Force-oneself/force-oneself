package com.quan.demo.spring;

import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-03
 **/
@Configuration
public class BeanConfig {

    public BeanConfig() {
        System.err.println("BeanConfig 执行构造器");
    }

    @Bean
    public SpringBeanExpandLifecycle springBeanExpandLifecycle() {
        return new SpringBeanExpandLifecycle();
    }

    @Bean
    public InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor() {
        return new MyInstantiationAwareBeanPostProcessor();
    }

    @Bean
    public BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return new MyBeanDefinitionRegistryPostProcessor();
    }
}
