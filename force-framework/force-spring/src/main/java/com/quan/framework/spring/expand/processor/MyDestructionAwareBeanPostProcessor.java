package com.quan.framework.spring.expand.processor;

import com.quan.common.util.AtomicUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.lang.NonNull;

/**
 * @author Force-oneself
 * @Description MyDestructionAwareBeanPostProcessor.java
 * @date 2021-07-12
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        AtomicUtils.print("DestructionAwareBeanPostProcessor ==> postProcessBeforeDestruction");
    }

    @Override
    public boolean requiresDestruction(@NonNull Object bean) {
        AtomicUtils.print("DestructionAwareBeanPostProcessor ==> requiresDestruction");
        return true;
    }
}
