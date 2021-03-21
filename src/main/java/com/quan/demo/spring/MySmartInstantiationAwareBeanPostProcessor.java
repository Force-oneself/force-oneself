package com.quan.demo.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import java.lang.reflect.Constructor;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-04
 **/
public class MySmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        System.err.println("SmartInstantiationAwareBeanPostProcessor ==> determineCandidateConstructors");
        return new Constructor[0];
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        System.err.println("SmartInstantiationAwareBeanPostProcessor ==> getEarlyBeanReference");
        return bean;
    }
}
