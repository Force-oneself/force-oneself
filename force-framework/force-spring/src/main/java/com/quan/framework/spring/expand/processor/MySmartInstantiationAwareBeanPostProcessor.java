package com.quan.framework.spring.expand.processor;

import com.quan.common.util.AtomicUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.lang.NonNull;

import java.lang.reflect.Constructor;

/**
 * @Description:
 * @Author Force-oneself
 * @Date 2021-03-04
 **/
public class MySmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    @Override
    public Constructor<?>[] determineCandidateConstructors(@NonNull Class<?> beanClass,
                                                           @NonNull String beanName) throws BeansException {
        AtomicUtils.print("SmartInstantiationAwareBeanPostProcessor ==> determineCandidateConstructors" +
                "可以改变Bean实例化的构造方法");
        return null;
    }

    @Override
    @NonNull
    public Object getEarlyBeanReference(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        AtomicUtils.print("SmartInstantiationAwareBeanPostProcessor ==> getEarlyBeanReference 获取早期暴露的Bean");
        return bean;
    }

    @Override
    public Class<?> predictBeanType(@NonNull Class<?> beanClass, @NonNull String beanName) throws BeansException {
        AtomicUtils.print("SmartInstantiationAwareBeanPostProcessor ==> predictBeanType");
        return null;
    }
}
