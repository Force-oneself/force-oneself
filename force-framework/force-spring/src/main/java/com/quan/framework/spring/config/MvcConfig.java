package com.quan.framework.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Force-oneself
 * @Description MvcConfig
 * @date 2021-08-24
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final List<HandlerInterceptor> interceptors;

    public MvcConfig(List<HandlerInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        interceptors.forEach(registry::addInterceptor);
    }
}
