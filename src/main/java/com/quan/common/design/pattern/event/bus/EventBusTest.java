package com.quan.common.design.pattern.event.bus;

public class EventBusTest {

    public static void main(String[] args) {
        Bus bus = new EventBus("testBus");
        bus.register(new SimpleObject());
        bus.post("Hello");
        bus.post("Hello", "test-topic");
    }
}
