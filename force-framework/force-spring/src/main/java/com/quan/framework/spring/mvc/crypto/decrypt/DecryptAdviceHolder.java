package com.quan.framework.spring.mvc.crypto.decrypt;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;

import java.lang.reflect.Type;

/**
 * @author Force-oneself
 * @date 2023-08-21
 */
public class DecryptAdviceHolder {

    private HttpInputMessage inputMessage;
    private MethodParameter parameter;
    private Type targetType;
    private Class<? extends HttpMessageConverter<?>> converterType;


    public static DecryptAdviceHolder of(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                         Class<? extends HttpMessageConverter<?>> converterType) {
        DecryptAdviceHolder holder = new DecryptAdviceHolder();
        holder.setInputMessage(inputMessage);
        holder.setParameter(parameter);
        holder.setTargetType(targetType);
        holder.setConverterType(converterType);
        return holder;
    }

    public HttpInputMessage getInputMessage() {
        return inputMessage;
    }

    public void setInputMessage(HttpInputMessage inputMessage) {
        this.inputMessage = inputMessage;
    }

    public MethodParameter getParameter() {
        return parameter;
    }

    public void setParameter(MethodParameter parameter) {
        this.parameter = parameter;
    }

    public Type getTargetType() {
        return targetType;
    }

    public void setTargetType(Type targetType) {
        this.targetType = targetType;
    }

    public Class<? extends HttpMessageConverter<?>> getConverterType() {
        return converterType;
    }

    public void setConverterType(Class<? extends HttpMessageConverter<?>> converterType) {
        this.converterType = converterType;
    }
}
