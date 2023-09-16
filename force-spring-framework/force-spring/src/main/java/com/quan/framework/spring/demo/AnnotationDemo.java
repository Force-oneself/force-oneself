package com.quan.framework.spring.demo;

import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Force-oneself
 * @description AnnotationDemo
 * @date 2022-02-09
 */
// Spring context 注解
@Lazy
@Primary
@ComponentScan
@ComponentScans(value = {@ComponentScan})
@Conditional(AnnotationDemo.class)
@Configuration
@DependsOn
@Description("")
@EnableAspectJAutoProxy
@EnableLoadTimeWeaving
@EnableMBeanExport
@Import({AnnotationDemo.class})
@ImportResource
@Profile("test")
@PropertySource("")
@PropertySources({})
@Role(1)
@Scope
public class AnnotationDemo implements Condition {

    @Bean
    public Object method() {
        return new Object();
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
    }
}
