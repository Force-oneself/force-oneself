package com.quan.framework.spring.config;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Force-oneself
 * @Description MyApplicationContext.java
 * @date 2021-07-04
 */
public class MyApplicationContext extends AnnotationConfigApplicationContext {

    public MyApplicationContext(Class<BeanConfig> beanConfigClass) {
        super(beanConfigClass);
    }

    @Override
    protected void finishRefresh() {
        throw new BeansException("测试Bean销毁") {
        };
    }
}
