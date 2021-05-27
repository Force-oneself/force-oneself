package com.quan.demo.framework.spring;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description: class EnableConfig
 * @Author Force丶Oneself
 * @Date 2021-05-27
 **/
@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableWebSecurity
@EnableScheduling
@EnableCaching
@EnableWebMvc
@EnableAsync
public class EnableConfig {

}
