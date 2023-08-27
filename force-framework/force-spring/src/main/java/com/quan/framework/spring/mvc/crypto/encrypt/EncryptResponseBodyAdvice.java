package com.quan.framework.spring.mvc.crypto.encrypt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.common.bean.R;
import com.quan.framework.spring.mvc.crypto.CryptoHelper;
import com.quan.framework.spring.mvc.crypto.annotation.Encrypt;
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

    private final EncryptHandler handler;
    private final ObjectMapper objectMapper;

    public EncryptResponseBodyAdvice(EncryptHandler handler, ObjectMapper objectMapper) {
        this.handler = handler;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return CryptoHelper.isAnnotated(methodParameter,  Encrypt.class);
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        if (body == null) {
            return null;
        }
        EncryptAdviceHolder holder = EncryptAdviceHolder.beforeOf(body, returnType, selectedContentType, selectedConverterType, request, response);
        Class<?> realReturnClass = body.getClass();
        if (R.class.isAssignableFrom(realReturnClass)) {
            R<Object> realBody = (R<Object>) body;
            Object data = realBody.getData();
            if (data != null) {
                try {
                    byte[] encrypt = handler.encrypt(holder, objectMapper.writeValueAsBytes(data));
                    realBody.setData(new String(encrypt, StandardCharsets.UTF_8));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            return realBody;
        }
        try {
            byte[] encrypt = handler.encrypt(holder, objectMapper.writeValueAsBytes(body));
            return new String(encrypt, StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
