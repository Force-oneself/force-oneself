package com.quan.pattern.multi.thread.event.bus;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Registry {

    // 存储Subscriber集合和Topic之间关系的map
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> subscriberContainer = new ConcurrentHashMap<>();

    public void bind(Object subscriber) {
        // 获取Subscriber Object 的方法集合然后进行绑定
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        subscribeMethods.forEach(m -> tierSubscriber(subscriber, m));
    }

    public void unbind(Object subscriber) {
        // unbind 为了提高速度，只对Subscriber进行失效操作
        subscriberContainer.forEach((key, queue) -> queue.forEach(s -> {
            if (s.getSubscribeObject() == subscriber) {
                s.setDisable(true);
            }
        }));
    }

    public ConcurrentLinkedQueue<Subscriber> scanSubscriber(final String topic) {
        return subscriberContainer.get(topic);
    }

    private void tierSubscriber(Object subscriber, Method method) {
        final Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
        String topic = subscribe.topic();
        // 当某topic没有Subscriber Queue到时候创建一个
        subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
        // 创建一个Subscriber并且加入Subscriber列表中
        subscriberContainer.get(topic).add(new Subscriber(subscriber, method));
    }

    private List<Method> getSubscribeMethods(Object subscriber) {
        final List<Method> methods = new ArrayList<>();
        Class<?> temp = subscriber.getClass();
        // 不断获取当前类和父类的所有@Subscribe方法
        while (temp != null) {
            // 获取所有的方法
            Method[] declareMethods = temp.getDeclaredMethods();
            // 只有public方法&&有一个入参&&最重要的是被@Subscribe标记的方法才符合回调方法
            Arrays.stream(declareMethods).filter(m -> m.isAnnotationPresent(Subscribe.class)
                    && m.getParameterCount() == 1
                    && m.getModifiers() == Modifier.PUBLIC).forEach(methods::add);
            temp = temp.getSuperclass();
        }
        return methods;
    }
}
