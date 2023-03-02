package com.quan.framework.spring.mvc.xss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.DispatcherType;
import javax.servlet.ServletRequest;

/**
 * @author Force-oneself
 * @date 2023-03-02
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ServletRequest.class)
@EnableConfigurationProperties(XssProperties.class)
public class XssAutoConfig {

    @Bean
    public FilterRegistrationBean<XssRequestFilter> xssFilterRegistration(XssProperties properties) {
        FilterRegistrationBean<XssRequestFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssRequestFilter(properties));
        registration.addUrlPatterns("/*");
        registration.setName("xssRequestFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 50);
        return registration;
    }
}
