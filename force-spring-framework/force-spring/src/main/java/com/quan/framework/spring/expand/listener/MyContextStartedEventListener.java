package com.quan.framework.spring.expand.listener;

import com.quan.common.util.AtomicUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author Force-oneself
 * @Description MyContextRefreshedEvent.java
 * @date 2021-07-14
 */
@Component
public class MyContextStartedEventListener implements ApplicationListener<ContextStartedEvent> {

    @Override
    public void onApplicationEvent(@NonNull ContextStartedEvent event) {
        AtomicUtils.print("MyContextStartedEventListener ==> onApplicationEvent");
    }
}
