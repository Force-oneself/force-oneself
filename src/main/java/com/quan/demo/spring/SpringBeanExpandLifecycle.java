package com.quan.demo.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Description: Spring bean 加载生命周期拓展执行顺序分析
 * @Author heyq
 * @Date 2021-03-03
 **/
public class SpringBeanExpandLifecycle implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private String name;

    public void setName(String name) {
        System.err.println("SpringBeanExpandLifecycle setName函数执行");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SpringBeanExpandLifecycle() {
        System.err.println("SpringBeanExpandLifecycle 构造函数执行");
    }

    @Override
    public void setBeanName(String name) {
        System.err.println("BeanNameWare ==> setBeanName");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.err.println("BeanClassLoaderWare ==> setBeanClassLoader");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.err.println("BeanFactoryWare ==> setBeanFactory");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("InitializingBean ==> afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.err.println("DisposableBean ==> destroy");
    }
}
