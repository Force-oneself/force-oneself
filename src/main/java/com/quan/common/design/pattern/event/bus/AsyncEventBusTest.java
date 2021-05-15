package com.quan.common.design.pattern.event.bus;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AsyncEventBusTest {

    public static void main(String[] args) {
        Bus bus = new AsyncEventBus("testBus", (ThreadPoolExecutor) Executors.newFixedThreadPool(10));
        bus.register(new SimpleObject());
        bus.post("async");
        bus.post("async", "alex-topic");
    }
}
