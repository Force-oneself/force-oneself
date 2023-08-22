package com.quan.framework.spring.mvc.crypto.decrypt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.framework.spring.mvc.crypto.annotation.Decrypt;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Force-oneself
 * @date 2023-03-03
 */
public class DecryptArgumentResolver implements HandlerMethodArgumentResolver {

    private final ObjectMapper objectMapper;

    public DecryptArgumentResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return AnnotatedElementUtils.hasAnnotation(parameter.getParameter(), Decrypt.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws IOException {
        Parameter parameter = methodParameter.getParameter();
        String parameterName = methodParameter.getParameterName();
        if (parameterName == null) {
            return null;
        }
        String text = webRequest.getParameter(parameterName);
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        // TODO 解密
        Decrypt decrypt = AnnotatedElementUtils.getMergedAnnotation(parameter, Decrypt.class);
        text += "：已加密";
        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
        Class<?> parameterType = parameter.getType();
        if (parameterType == String.class) {
            return new String(textBytes);
        }
        if (parameterType == Object.class || parameterType == Map.class) {
            parameterType = LinkedHashMap.class;
        }
        return objectMapper.readValue(textBytes, parameterType);
    }

}
