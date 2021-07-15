package com.quan.framework.spring.expand.listener;

import com.quan.common.util.AtomicUtils;
import com.quan.framework.spring.expand.event.TestEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author Force-oneself
 * @Description TestEventListener.java
 * @date 2021-07-14
 */
@Component
public class TestEventListener implements ApplicationListener<TestEvent> {

    @Override
    public void onApplicationEvent(@NonNull TestEvent event) {
        AtomicUtils.print("TestEventListener ==> onApplicationEvent:" + event);
    }
}
