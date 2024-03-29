package com.quan.boot.mvc.config;

import com.quan.boot.mvc.constant.PropConstant;
import com.quan.boot.mvc.exception.ExceptionFilter;
import com.quan.boot.mvc.exception.ExceptionHandlerAdvice;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.DispatcherType;
import javax.servlet.ServletRequest;

/**
 * @author Force-oneself
 * @date 2023-03-07
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ServletRequest.class)
@ConditionalOnProperty(prefix = PropConstant.EXCEPTION, name = "enable", matchIfMissing = true)
@Import(ExceptionHandlerAdvice.class)
public class ExceptionAutoConfig {

    @Bean
    public FilterRegistrationBean<ExceptionFilter> exceptionFilter(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
        FilterRegistrationBean<ExceptionFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new ExceptionFilter(handlerExceptionResolver));
        registration.addUrlPatterns("/*");
        registration.setName("exceptionFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 50);
        return registration;
    }
}




