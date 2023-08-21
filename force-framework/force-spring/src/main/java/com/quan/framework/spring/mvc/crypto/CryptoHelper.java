package com.quan.framework.spring.mvc.crypto;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Force-oneself
 * @date 2023-08-18
 */
public class CryptoHelper {

    public static <T extends Annotation> T find(MethodParameter parameter, Class<T> anno) {
        Method method = parameter.getMethod();
        return method != null && AnnotatedElementUtils.isAnnotated(method, anno)
                ? AnnotatedElementUtils.findMergedAnnotation(parameter.getMethod(), anno)
                : AnnotatedElementUtils.findMergedAnnotation(parameter.getDeclaringClass(), anno);
    }

    public static <T extends Annotation> boolean isAnnotated(MethodParameter parameter, Class<T> anno) {
        if (parameter == null || parameter.getMethod() == null) {
            return false;
        }
        // 先找方法，再找方法上的类
        // 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
        return AnnotatedElementUtils.isAnnotated(parameter.getMethod(), anno)
                || AnnotatedElementUtils.isAnnotated(parameter.getDeclaringClass(), anno);
    }
}
