package com.quan.demo.thread;

import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;

import javax.annotation.Nonnull;

/**
 * 线程本地变量监听器
 *
 * @author Force-oneself
 * @date 2025-01-20
 */
public class ThreadLocalApplicationListener implements ApplicationListener<EnvironmentChangeEvent> {

    private final ThreadPoolManager threadPoolManager;

    public ThreadLocalApplicationListener(ThreadPoolManager threadPoolManager) {
        this.threadPoolManager = threadPoolManager;
    }

    @Override
    public void onApplicationEvent(@Nonnull EnvironmentChangeEvent event) {
        threadPoolManager.change(event.getKeys());
    }
}
