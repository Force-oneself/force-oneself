package com.quan.framework.spring.expand.processor;

import com.quan.common.util.AtomicUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.lang.NonNull;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-03
 **/
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(@NonNull Class<?> beanClass, @NonNull String beanName)
            throws BeansException {
        AtomicUtils.print("InstantiationAwareBeanPostProcessor ==> postProcessBeforeInstantiation");
        // 这里返回Object不为null，则不会走spring的创建过程直接返回对象
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        AtomicUtils.print("InstantiationAwareBeanPostProcessor ==> postProcessAfterInstantiation");
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(@NonNull PropertyValues pvs, @NonNull Object bean,
                                                @NonNull String beanName) throws BeansException {
        AtomicUtils.print("InstantiationAwareBeanPostProcessor ==> postProcessProperties");
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        AtomicUtils.print("InstantiationAwareBeanPostProcessor ==> postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        AtomicUtils.print("InstantiationAwareBeanPostProcessor ==> postProcessAfterInitialization");
        return bean;
    }
}
