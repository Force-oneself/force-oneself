package com.quan.framework.spring.expand.processor;

import com.quan.common.util.AtomicUtils;
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
        AtomicUtils.print("BeanPostProcessor ==> postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        AtomicUtils.print("BeanPostProcessor ==> postProcessAfterInitialization");
        return bean;
    }
}
