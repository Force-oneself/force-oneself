package com.quan.boot.mvc.config;

import com.quan.boot.mvc.exception.ExceptionFilter;
import com.quan.boot.mvc.exception.ExceptionHandlerAdvice;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.DispatcherType;

/**
 * @author Force-oneself
 * @date 2023-03-07
 */
@Configuration(proxyBeanMethods = false)
@Import(ExceptionHandlerAdvice.class)
@ConditionalOnProperty(prefix = "force.servlet.exception", name = "enable", matchIfMissing = true)
public class ExceptionAutoConfig {

    @Bean
    public FilterRegistrationBean<ExceptionFilter> exceptionFilter(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
        FilterRegistrationBean<ExceptionFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new ExceptionFilter(handlerExceptionResolver));
        registration.addUrlPatterns("/*");
        registration.setName("exceptionFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 10);
        return registration;
    }
}
