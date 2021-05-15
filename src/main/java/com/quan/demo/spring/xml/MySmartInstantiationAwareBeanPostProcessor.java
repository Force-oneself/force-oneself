package com.quan.demo.spring.xml;

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
        System.err.println(AtomicIntegerUtils.atomic.getAndIncrement() + " ==> SmartInstantiationAwareBeanPostProcessor ==> determineCandidateConstructors可以改变Bean实例化的构造方法");
        return null;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        System.err.println(AtomicIntegerUtils.atomic.getAndIncrement() + " ==> SmartInstantiationAwareBeanPostProcessor ==> getEarlyBeanReference");
        return bean;
    }
}
