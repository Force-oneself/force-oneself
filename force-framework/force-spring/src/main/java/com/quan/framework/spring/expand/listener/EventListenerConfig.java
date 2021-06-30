package com.quan.framework.spring.expand.listener;

import com.quan.common.util.AtomicUtils;
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
        AtomicUtils.print("ApplicationStartedEvent执行");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        AtomicUtils.print("ApplicationReadyEvent执行");
    }

    @EventListener(ApplicationStartingEvent.class)
    public void starting() {
        AtomicUtils.print("ApplicationStartingEvent执行");
    }

    @EventListener(ApplicationFailedEvent.class)
    public void failed() {
        AtomicUtils.print("ApplicationFailedEvent执行");
    }

    @EventListener(ApplicationPreparedEvent.class)
    public void prepare() {
        AtomicUtils.print("ApplicationPreparedEvent执行");
    }

    @EventListener(ApplicationContextInitializedEvent.class)
    public void contextInitialized() {
        AtomicUtils.print("ApplicationContextInitializedEvent执行");
    }

    @EventListener(ApplicationEnvironmentPreparedEvent.class)
    public void environmentPrepared() {
        AtomicUtils.print("ApplicationEnvironmentPreparedEvent执行");
    }

}
