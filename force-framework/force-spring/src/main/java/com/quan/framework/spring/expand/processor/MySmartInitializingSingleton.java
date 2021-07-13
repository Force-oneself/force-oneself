package com.quan.framework.spring.expand.processor;

import com.quan.common.util.AtomicUtils;
import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 * @author Force-oneself
 * @Description MySmartInitializingSingleton.java
 * @date 2021-07-12
 */
public class MySmartInitializingSingleton implements SmartInitializingSingleton {

    @Override
    public void afterSingletonsInstantiated() {
        AtomicUtils.print("SmartInitializingSingleton ==> afterSingletonsInstantiated");
    }
}