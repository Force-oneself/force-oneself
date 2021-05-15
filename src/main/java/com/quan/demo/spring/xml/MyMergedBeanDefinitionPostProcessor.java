package com.quan.demo.spring.xml;

import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-04
 **/
public class MyMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {
    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        System.err.println(AtomicIntegerUtils.atomic.getAndIncrement() + " ==> MergedBeanDefinitionPostProcessor ==> postProcessMergedBeanDefinition");
    }
}
