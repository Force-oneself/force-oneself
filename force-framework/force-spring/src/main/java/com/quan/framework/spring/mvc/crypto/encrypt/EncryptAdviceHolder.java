package com.quan.framework.spring.mvc.crypto.encrypt;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

/**
 * @author Force-oneself
 * @date 2023-08-23
 */
public class EncryptAdviceHolder {

    private Object body;
    private MethodParameter returnType;
    private MediaType selectedContentType;
    private Class<? extends HttpMessageConverter<?>> selectedConverterType;
    private ServerHttpRequest request;
    private ServerHttpResponse response;

    public static EncryptAdviceHolder beforeOf(Object body,
                                               MethodParameter returnType,
                                               MediaType selectedContentType,
                                               Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                               ServerHttpRequest request,
                                               ServerHttpResponse response) {
        EncryptAdviceHolder holder = new EncryptAdviceHolder();
        holder.setBody(body);
        holder.setReturnType(returnType);
        holder.setSelectedContentType(selectedContentType);
        holder.setSelectedConverterType(selectedConverterType);
        holder.setRequest(request);
        holder.setResponse(response);
        return holder;
    }


    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public MethodParameter getReturnType() {
        return returnType;
    }

    public void setReturnType(MethodParameter returnType) {
        this.returnType = returnType;
    }

    public MediaType getSelectedContentType() {
        return selectedContentType;
    }

    public void setSelectedContentType(MediaType selectedContentType) {
        this.selectedContentType = selectedContentType;
    }

    public Class<? extends HttpMessageConverter<?>> getSelectedConverterType() {
        return selectedConverterType;
    }

    public void setSelectedConverterType(Class<? extends HttpMessageConverter<?>> selectedConverterType) {
        this.selectedConverterType = selectedConverterType;
    }

    public ServerHttpResponse getResponse() {
        return response;
    }

    public void setResponse(ServerHttpResponse response) {
        this.response = response;
    }

    public ServerHttpRequest getRequest() {
        return request;
    }

    public void setRequest(ServerHttpRequest request) {
        this.request = request;
    }
}
