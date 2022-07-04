package com.quan.demo.framework.desensitization.config;

import com.quan.demo.framework.desensitization.DesensitizationPostProcessor;
import com.quan.demo.framework.desensitization.Operation;
import com.quan.demo.framework.desensitization.annotation.Desensitization;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.List;

/**
 * DesensitizationAutoConfiguration
 *
 * @author Force-oneself
 * @date 2022-07-03
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({Jackson2ObjectMapperBuilder.class})
public class DesensitizationAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DesensitizationPostProcessor desensitizationPostProcessor(List<Operation> operations) {
        return new DesensitizationPostProcessor(operations);
    }

    @Bean
    public Operation noOperation() {
        return new Desensitization.NoOperation();
    }
}
