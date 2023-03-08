package com.quan.framework.spring.mvc.crypto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Force-oneself
 * @date 2023-03-03
 */
@Configuration(proxyBeanMethods = false)
@Import({DecryptRequestBodyAdvice.class, EncryptResponseBodyAdvice.class})
public class CryptoAutoConfig implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    public CryptoAutoConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new DecryptParamResolver(objectMapper));
    }
}
