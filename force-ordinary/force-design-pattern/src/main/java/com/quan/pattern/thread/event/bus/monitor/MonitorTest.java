package com.quan.pattern.thread.event.bus.monitor;

import com.quan.pattern.thread.event.bus.AsyncEventBus;
import com.quan.pattern.thread.event.bus.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MonitorTest {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        final EventBus eventBus = new AsyncEventBus(executor);
        eventBus.register(new FileChangeListener());
        DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "D:\\EventBusTest");
        monitor.startMonitor();
    }
}
