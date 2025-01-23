package com.quan.log.config;


import com.quan.log.data.datasource.DataSourceBeanPostProcessor;
import org.springframework.context.annotation.Bean;

public class ForceLogConfiguration {

    @Bean
    public DataSourceBeanPostProcessor dataSourceBeanPostProcessor() {
        return new DataSourceBeanPostProcessor();
    }
}
