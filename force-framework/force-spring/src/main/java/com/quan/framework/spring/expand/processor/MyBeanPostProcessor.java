package com.quan.framework.spring.expand.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-04
 **/
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.err.println(AtomicUtils.atomic.getAndIncrement() + " ==> BeanPostProcessor ==> postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.println(AtomicUtils.atomic.getAndIncrement() + " ==> BeanPostProcessor ==> postProcessAfterInitialization");
        return bean;
    }
}
