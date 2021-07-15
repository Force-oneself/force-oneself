package com.quan.framework.spring.expand;

import com.quan.common.util.AtomicUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;

/**
 * @author Force-oneself
 * @Description EventListenerConfig.java
 * @date 2021-07-14
 */
@Configuration
public class EventListenerConfig {

    @EventListener(ContextClosedEvent.class)
    public void contextClosedEvent() {
        AtomicUtils.print("EventListener ==> contextClosedEvent");
    }

    @EventListener(ContextStartedEvent.class)
    public void contextStartedEvent() {
        AtomicUtils.print("EventListener ==> contextStartedEvent");
    }

    @EventListener(ContextStoppedEvent.class)
    public void contextStoppedEvent() {
        AtomicUtils.print("EventListener ==> contextStoppedEvent");
    }
}
