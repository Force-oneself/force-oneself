package com.quan.framework.spring.mvc.crypto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @author Force-oneself
 * @date 2023-03-03
 */
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    // private final ApiCryptoProperties properties;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, @NonNull Class converterType) {
        // 先找方法，再找方法上的类
        Method method = methodParameter.getMethod();
        if (method == null) {
            return false;
        }
        boolean isMethodAnnotated = AnnotatedElementUtils.isAnnotated(method, Encrypt.class);
        if (isMethodAnnotated) {
            return true;
        }
        // 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
        return AnnotatedElementUtils.isAnnotated(methodParameter.getDeclaringClass(), Encrypt.class);
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        if (body == null) {
            return null;
        }
        // TODO 加密
        byte[] bodyJsonBytes = null;
        try {
            bodyJsonBytes = objectMapper.writeValueAsBytes(body);
        } catch (JsonProcessingException ignore) {
            // throw new RuntimeException(e);
        }
        if (bodyJsonBytes == null) {
            return body;
        }
        response.getHeaders()
                .setContentType(MediaType.TEXT_PLAIN);
        return new String(bodyJsonBytes);
    }

}
