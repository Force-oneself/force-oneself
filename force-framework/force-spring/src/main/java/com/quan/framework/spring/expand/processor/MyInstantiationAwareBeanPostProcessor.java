package com.quan.framework.spring.expand.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-03
 **/
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.err.println(AtomicUtils.atomic.getAndIncrement() + " ==> InstantiationAwareBeanPostProcessor ==> postProcessBeforeInstantiation");
        // 这里返回Object不为null，则不会走spring的创建过程直接返回对象
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.err.println(AtomicUtils.atomic.getAndIncrement() + " ==> InstantiationAwareBeanPostProcessor ==> postProcessAfterInstantiation");
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.err.println(AtomicUtils.atomic.getAndIncrement() + " ==> InstantiationAwareBeanPostProcessor ==> postProcessProperties");
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.err.println(AtomicUtils.atomic.getAndIncrement() + " ==> InstantiationAwareBeanPostProcessor ==> postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.println(AtomicUtils.atomic.getAndIncrement() + " ==> InstantiationAwareBeanPostProcessor ==> postProcessAfterInitialization");
        return bean;
    }
}
