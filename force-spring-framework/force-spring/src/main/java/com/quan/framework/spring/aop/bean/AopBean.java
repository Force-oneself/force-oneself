package com.quan.framework.spring.aop.bean;

import org.springframework.stereotype.Component;

/**
 * @author Force-oneself
 * @Description AopBean.java
 * @date 2021-07-12
 */
@Component
public class AopBean {

    public Object aop() {
        System.out.println("target method");
        return new Object();
    }
}
