package com.quan.framework.spring.mvc.crypto;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author Force-oneself
 * @date 2023-03-03
 */
@ControllerAdvice
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {
    // private final ApiCryptoProperties properties;

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter,
                            @NonNull Type targetType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        // 先找方法，再找方法上的类
        Method method = methodParameter.getMethod();
        if (method == null) {
            return false;
        }
        boolean isMethodAnnotated = AnnotatedElementUtils.isAnnotated(method, Decrypt.class);
        if (isMethodAnnotated) {
            return true;
        }
        // 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
        return AnnotatedElementUtils.isAnnotated(methodParameter.getDeclaringClass(), Decrypt.class);
    }

    @Override
    public Object handleEmptyBody(Object body,
                                  @NonNull HttpInputMessage inputMessage,
                                  @NonNull MethodParameter parameter,
                                  @NonNull Type targetType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @NonNull
    @Override
    public HttpInputMessage beforeBodyRead(@NonNull HttpInputMessage inputMessage,
                                           @NonNull MethodParameter parameter,
                                           @NonNull Type targetType,
                                           @NonNull Class<? extends HttpMessageConverter<?>> converterType)
            throws IOException {
        Method method = parameter.getMethod();
        if (method == null) {
            return inputMessage;
        }
        // 判断 body 是否为空
        InputStream messageBody = inputMessage.getBody();
        if (messageBody.available() <= 0) {
            return inputMessage;
        }

        Decrypt decrypt = AnnotatedElementUtils.getMergedAnnotation(method, Decrypt.class);

        // base64 byte array
        byte[] bodyByteArray = StreamUtils.copyToByteArray(messageBody);
        // TODO 解密
        byte[] decryptedBody = bodyByteArray;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(decryptedBody);

        return new HttpInputMessage() {
            @Override
            @NonNull
            public InputStream getBody() {
                return inputStream;
            }

            @Override
            @NonNull
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
        };
    }

    @NonNull
    @Override
    public Object afterBodyRead(@NonNull Object body,
                                @NonNull HttpInputMessage inputMessage,
                                @NonNull MethodParameter parameter,
                                @NonNull Type targetType,
                                @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

}
