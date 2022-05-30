package com.quan.pattern.thread.event.bus;

/**
 * 非常普通的对象
 */
public class SimpleObject {

    @Subscribe(topic = "alex-topic")
    public void alexTopic(String message) {
        System.out.println("alex-topic: " + message);
    }

    @Subscribe(topic = "test-topic")
    public void testTopic(String message) {
        System.out.println("test-topic: " + message);
    }

    @Subscribe
    public void defaultTopic(String message) {
        System.out.println("default-topic: " + message);
    }
}
