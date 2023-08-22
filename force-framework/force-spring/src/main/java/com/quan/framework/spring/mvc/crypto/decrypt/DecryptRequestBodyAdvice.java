package com.quan.framework.spring.mvc.crypto.decrypt;

import com.quan.framework.spring.mvc.crypto.CryptoHelper;
import com.quan.framework.spring.mvc.crypto.annotation.Decrypt;
import org.springframework.core.MethodParameter;
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
import java.util.List;

/**
 * @author Force-oneself
 * @date 2023-03-03
 */
@ControllerAdvice
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

     private final List<BodyAdviceDecryptorHandler> handlers;

    public DecryptRequestBodyAdvice(List<BodyAdviceDecryptorHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter,
                            @NonNull Type targetType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return CryptoHelper.isAnnotated(methodParameter, Decrypt.class);
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
        byte[] ciphertext = StreamUtils.copyToByteArray(messageBody);
        final DecryptAdviceHolder holder = DecryptAdviceHolder.of(inputMessage, parameter, targetType, converterType);
        byte[] decryptedBody = handlers.stream()
                .filter(h -> h.support(holder))
                .findFirst()
                .map(h -> h.decryptor(holder))
                .map(decryptor -> decryptor.decrypt(ciphertext))
                .orElse(ciphertext);

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
