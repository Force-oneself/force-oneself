package com.quan.demo.framework.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Force-oneself
 * @Description RestControllerResponseAdvice.java
 * @date 2021-07-27
 */
//@RestControllerAdvice(basePackages = "com.quan.demo.controller")
public class RestControllerRequestAdvice implements RequestBodyAdvice {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper objectMapper;

    public RestControllerRequestAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter,
                            @NonNull Type targetType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    @NonNull
    public HttpInputMessage beforeBodyRead(@NonNull HttpInputMessage inputMessage,
                                           @NonNull MethodParameter parameter,
                                           @NonNull Type targetType,
                                           @NonNull Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        System.out.println("RestControllerRequestAdvice == beforeBodyRead");
        return inputMessage;
    }

    @Override
    @NonNull
    public Object afterBodyRead(@NonNull Object body,
                                @NonNull HttpInputMessage inputMessage,
                                @NonNull MethodParameter parameter,
                                @NonNull Type targetType,
                                @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("RestControllerRequestAdvice == afterBodyRead");
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body,
                                  @NonNull HttpInputMessage inputMessage,
                                  @NonNull MethodParameter parameter,
                                  @NonNull Type targetType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("RestControllerRequestAdvice == handleEmptyBody");
        return body;
    }
}
