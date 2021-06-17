package com.quan.framework.spring.expand.listener;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * spring事件监听使用
 *
 * @Description:
 * @Author heyq
 * @Date 2021-04-22
 **/
@Component
public class EventListenerConfig {

    @EventListener(ApplicationStartedEvent.class)
    public void start() {
        System.err.println("ApplicationStartedEvent执行");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        System.err.println("ApplicationReadyEvent执行");
    }

    @EventListener(ApplicationStartingEvent.class)
    public void starting() {
        System.err.println("ApplicationStartingEvent执行");
    }

    @EventListener(ApplicationFailedEvent.class)
    public void failed() {
        System.err.println("ApplicationFailedEvent执行");
    }

    @EventListener(ApplicationPreparedEvent.class)
    public void prepare() {
        System.err.println("ApplicationPreparedEvent执行");
    }

    @EventListener(ApplicationContextInitializedEvent.class)
    public void contextInitialized() {
        System.err.println("ApplicationContextInitializedEvent执行");
    }

    @EventListener(ApplicationEnvironmentPreparedEvent.class)
    public void environmentPrepared() {
        System.err.println("ApplicationEnvironmentPreparedEvent执行");
    }

}
