package com.quan.framework.spring.expand.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Constructor;

/**
 * @Description:
 * @Author Force-oneself
 * @Date 2021-03-04
 **/
public class MySmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
    @Override
    public Constructor<?>[] determineCandidateConstructors(@NotNull Class<?> beanClass,
                                                           @NotNull String beanName) throws BeansException {
        System.err.println(AtomicIntegerUtils.atomic.getAndIncrement() + " ==> SmartInstantiationAwareBeanPostProcessor ==> determineCandidateConstructors可以改变Bean实例化的构造方法");
        return null;
    }

    @Override
    public Object getEarlyBeanReference(@NotNull Object bean,
                                        @NotNull String beanName) throws BeansException {
        System.err.println(AtomicIntegerUtils.atomic.getAndIncrement() + " ==> SmartInstantiationAwareBeanPostProcessor ==> getEarlyBeanReference");
        return bean;
    }
}
