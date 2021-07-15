package com.quan.framework.spring.demo;

import com.quan.framework.spring.aop.bean.AopBean;
import com.quan.framework.spring.config.BeanConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Force-oneself
 * @Description AopDemo.java
 * @date 2021-07-12
 */
public class AopDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        AopBean bean = context.getBean(AopBean.class);
        bean.aop();
    }
}
