package com.quan.framework.spring.expand.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author Force-oneself
 * @Description MyFactoryBean.java
 * @date 2021-07-12
 */
public class MyFactoryBean implements FactoryBean<MyFactoryBean> {

    @Override
    public MyFactoryBean getObject() throws Exception {
        return new MyFactoryBean();
    }

    @Override
    public Class<?> getObjectType() {
        return MyFactoryBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
