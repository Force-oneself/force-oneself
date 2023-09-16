package com.quan.framework.spring.expand.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Force-oneself
 * @Description TestEvent.java
 * @date 2021-07-14
 */
public class TestEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public TestEvent(Object source) {
        super(source);
    }
}
