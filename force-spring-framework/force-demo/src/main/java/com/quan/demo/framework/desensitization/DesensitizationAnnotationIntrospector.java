package com.quan.demo.framework.desensitization;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.quan.demo.framework.desensitization.annotation.Desensitization;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DesensitizationAnnotationIntrospector
 *
 * @author Force-oneself
 * @date 2022-07-03
 */
public class DesensitizationAnnotationIntrospector extends NopAnnotationIntrospector {

    private final Map<Class<? extends Operation>, Operation> operationMap;

    public DesensitizationAnnotationIntrospector(List<Operation> operations) {
        operationMap = operations.stream().collect(Collectors.toMap(Operation::getClass, ops -> ops));
    }

    @Override
    public Object findSerializer(Annotated am) {
        Desensitization annotation = am.getAnnotation(Desensitization.class);
        if (annotation != null && operationMap.containsKey(annotation.ops())) {
            return new DesensitizationSerializer(operationMap.get(annotation.ops()));
        }
        return null;
    }
}
