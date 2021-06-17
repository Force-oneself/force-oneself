package com.quan.framework.spring.xml;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.context.annotation.Bean;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-03
 **/
//@Configuration
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

//    @Bean
    public static BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return new MyBeanDefinitionRegistryPostProcessor();
    }

    @Bean
    public BeanPostProcessor postProcessor() {
        return new MyBeanPostProcessor();
    }

    @Bean
    public SmartInstantiationAwareBeanPostProcessor smartInstantiationAwareBeanPostProcessor() {
        return new MySmartInstantiationAwareBeanPostProcessor();
    }

    @Bean
    public MergedBeanDefinitionPostProcessor mergedBeanDefinitionPostProcessor() {
        return new MyMergedBeanDefinitionPostProcessor();
    }
}
