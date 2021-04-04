package com.quan.demo.spring.xml;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-03
 **/
public class SpringDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(BeanConfig.class);
        SpringBeanExpandLifecycle bean = acac.getBean("springBeanExpandLifecycle", SpringBeanExpandLifecycle.class);
        System.err.println(bean);
    }
}
