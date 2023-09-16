package com.quan.boot.mvc.desensitization;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * DesensitizationPostProcessor
 *
 * @author Force-oneself
 * @date 2022-07-03
 */
public class DesensitizationPostProcessor implements BeanPostProcessor {

    private final List<Operation> operations;

    public DesensitizationPostProcessor(List<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if (!(bean instanceof ObjectMapper)) {
            return bean;
        }
        ObjectMapper objectMapper = (ObjectMapper) bean;
        AnnotationIntrospector ai = objectMapper.getSerializationConfig().getAnnotationIntrospector();
        AnnotationIntrospector newAi = AnnotationIntrospectorPair.pair(ai, new DesensitizationAnnotationIntrospector(operations));
        objectMapper.setAnnotationIntrospector(newAi);
        return objectMapper;
    }
}
