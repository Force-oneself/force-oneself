package com.quan.demo.framework.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.common.bean.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Force-oneself
 * @Description RestControllerResponseAdvice.java
 * @date 2021-07-27
 */
@RestControllerAdvice
public class RestControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper objectMapper;

    public RestControllerResponseAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(@NonNull MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (body instanceof String) {
            try {
                return R.ok(objectMapper.writeValueAsString(body));
            } catch (JsonProcessingException e) {
                log.error("格式转换错误，请检查【{}】", body);
                return R.fail("格式转换错误，请检查" + body);
            }
        }
        if (body instanceof R) {
            return body;
        }
        return R.ok(body);
    }
}
