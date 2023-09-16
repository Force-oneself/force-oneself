package com.quan.framework.spring.expand.bean;

import com.quan.common.util.AtomicUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.lang.NonNull;
import org.springframework.util.StringValueResolver;

/**
 * @Description: Spring bean 加载生命周期拓展执行顺序分析
 * @Author heyq
 * @Date 2021-03-03
 **/
public class SpringBeanExpandLifecycle implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        InitializingBean, DisposableBean, EnvironmentAware, ResourceLoaderAware, MessageSourceAware,
        EmbeddedValueResolverAware, ApplicationContextAware, ApplicationEventPublisherAware, LoadTimeWeaverAware,
        ImportAware {

    private String name;

    public void setName(String name) {
        AtomicUtils.print("SpringBeanExpandLifecycle setName函数执行");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SpringBeanExpandLifecycle() {
        AtomicUtils.print("SpringBeanExpandLifecycle 构造函数执行");
    }

    @Override
    public void setBeanName(@NonNull String name) {
        AtomicUtils.print("BeanNameAware ==> setBeanName");
    }

    @Override
    public void setBeanClassLoader(@NonNull ClassLoader classLoader) {
        AtomicUtils.print("BeanClassLoaderAware ==> setBeanClassLoader");
    }

    @Override
    public void setBeanFactory(@NonNull BeanFactory beanFactory) throws BeansException {
        AtomicUtils.print("BeanFactoryAware ==> setBeanFactory");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AtomicUtils.print("InitializingBean ==> afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        AtomicUtils.print("DisposableBean ==> destroy");
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        AtomicUtils.print("EnvironmentAware ==> setEnvironment" + environment);
    }

    @Override
    public void setResourceLoader(@NonNull ResourceLoader resourceLoader) {
        AtomicUtils.print("ResourceLoaderAware ==> setResourceLoader" + resourceLoader);
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        AtomicUtils.print("MessageSourceAware ==> setMessageSource" + messageSource);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        AtomicUtils.print("ApplicationContextAware ==> setApplicationContext" + applicationContext);
    }

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        AtomicUtils.print("ApplicationEventPublisherAware ==> setApplicationEventPublisher" + applicationEventPublisher);
    }

    @Override
    public void setEmbeddedValueResolver(@NonNull StringValueResolver resolver) {
        AtomicUtils.print("EmbeddedValueResolverAware ==> setEmbeddedValueResolver" + resolver);
    }

    @Override
    public void setImportMetadata(@NonNull AnnotationMetadata importMetadata) {
        AtomicUtils.print("ImportAware ==> setImportMetadata" + importMetadata);
    }

    @Override
    public void setLoadTimeWeaver(@NonNull LoadTimeWeaver loadTimeWeaver) {
        AtomicUtils.print("LoadTimeWeaverAware ==> setLoadTimeWeaver" + loadTimeWeaver);
    }
}
