package com.quan.framework.spring.mvc.crypto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.nio.charset.StandardCharsets;

/**
 * @author Force-oneself
 * @date 2023-03-03
 */
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    public EncryptResponseBodyAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return CryptoHelper.isAnnotated(methodParameter, Encrypt.class);
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
        Class<?> realReturnClass = body.getClass();
        if (String.class.isAssignableFrom(realReturnClass)) {
            return body;
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
        return new String(bodyJsonBytes, StandardCharsets.UTF_8);
    }

}
