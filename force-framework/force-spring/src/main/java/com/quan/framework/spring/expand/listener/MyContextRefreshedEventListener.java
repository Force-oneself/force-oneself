package com.quan.framework.spring.expand.listener;

import com.quan.common.util.AtomicUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author Force-oneself
 * @Description MyContextRefreshedEvent.java
 * @date 2021-07-14
 */
@Component
public class MyContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        AtomicUtils.print("MyContextRefreshedEventListener ==> onApplicationEvent");
    }
}
