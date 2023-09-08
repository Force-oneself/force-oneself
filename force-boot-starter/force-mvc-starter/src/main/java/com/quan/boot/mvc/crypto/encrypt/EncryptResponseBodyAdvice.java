package com.quan.boot.mvc.crypto.encrypt;

import com.quan.boot.mvc.crypto.CryptoHelper;
import com.quan.boot.mvc.crypto.annotation.Encrypt;
import com.quan.boot.mvc.crypto.exception.CryptoException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * @author Force-oneself
 * @date 2023-03-03
 */
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final List<BodyAdviceEncryptorHandler> handlers;

    public EncryptResponseBodyAdvice(List<BodyAdviceEncryptorHandler> handlers) {
        this.handlers = handlers;
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
        return handlers.stream()
                .filter(h -> h.support(holder))
                .findFirst()
                .map(h -> h.encrypt(holder))
                .orElseThrow(()-> new CryptoException("not found BodyAdviceDecryptorHandler"));
    }

}
