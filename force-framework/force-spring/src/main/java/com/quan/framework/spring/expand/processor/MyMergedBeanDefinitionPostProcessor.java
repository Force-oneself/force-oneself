package com.quan.framework.spring.expand.processor;

import com.quan.common.util.AtomicUtils;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.lang.NonNull;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-04
 **/
public class MyMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {

    @Override
    public void postProcessMergedBeanDefinition(@NonNull RootBeanDefinition beanDefinition, @NonNull Class<?> beanType,
                                                @NonNull String beanName) {
        AtomicUtils.print("MergedBeanDefinitionPostProcessor ==> postProcessMergedBeanDefinition");
    }

    @Override
    public void resetBeanDefinition(@NonNull String beanName) {
        AtomicUtils.print("MergedBeanDefinitionPostProcessor ==> resetBeanDefinition");
    }
}
