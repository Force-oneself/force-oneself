package com.quan.boot.mvc.crypto.decrypt;

import com.quan.boot.mvc.crypto.CryptoHelper;
import com.quan.boot.mvc.crypto.annotation.Decrypt;
import com.quan.boot.mvc.crypto.exception.CryptoException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * <span>@RequestBody</span> 切面实现解密
 *
 * @author Force-oneself
 * @date 2023-03-03
 * @see org.springframework.web.bind.annotation.ResponseBody
 * @see BodyAdviceDecryptorHandler
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
        final DecryptAdviceHolder holder = DecryptAdviceHolder.of(inputMessage, parameter, targetType, converterType);
        for (BodyAdviceDecryptorHandler handler : handlers) {
            if (handler.support(holder)) {
                return handler.decrypt(holder);
            }
        }
        throw new CryptoException("not found BodyAdviceDecryptorHandler");
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
