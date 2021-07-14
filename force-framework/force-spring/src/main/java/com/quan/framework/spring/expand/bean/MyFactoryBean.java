package com.quan.framework.spring.expand.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author Force-oneself
 * @Description MyFactoryBean.java
 * @date 2021-07-12
 */
public class MyFactoryBean implements FactoryBean<TestFactoryBean> {

    @Override
    public TestFactoryBean getObject() throws Exception {
        return new TestFactoryBean();
    }

    @Override
    public Class<?> getObjectType() {
        return TestFactoryBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
