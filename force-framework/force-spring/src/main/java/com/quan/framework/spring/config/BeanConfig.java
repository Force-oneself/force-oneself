package com.quan.framework.spring.config;

import com.quan.framework.spring.expand.processor.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-03
 **/
@Configuration
@ComponentScan("com.quan.framework.spring")
public class BeanConfig {

    public BeanConfig() {
        System.err.println("BeanConfig 执行构造器");
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringBeanExpandLifecycle springBeanExpandLifecycle() {
        return new SpringBeanExpandLifecycle();
    }

    @Bean
    public InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor() {
        return new MyInstantiationAwareBeanPostProcessor();
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
