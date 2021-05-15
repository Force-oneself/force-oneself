package com.quan.common.design.pattern.event.driven;

import java.util.HashMap;
import java.util.Map;

/**
 * 不是一个线程安全的类
 */
public class EventDispatcher implements DynamicRouter<Message> {

    // 用于保存Channel和Message之间的关系
    private final Map<Class<? extends Message>, Channel> routerTable;

    public EventDispatcher() {
        // 初始化RouterTable，但是在该实现中，我们使用的HashMap作为路由表
        this.routerTable = new HashMap<>();
    }

    /**
     * 在多线程的情况下，会引起数据不一致的的问题
     *
     * @param messageType
     * @param channel
     */
    @Override
    public void registerChannel(Class<? extends Message> messageType, Channel<? extends Message> channel) {
        this.routerTable.put(messageType, channel);
    }

    @Override
    public void dispatch(Message message) {
        if (routerTable.containsKey(message.getType())) {
            // 直接获取对应的Channel处理Message
            routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatcherException("Can't match the channel for [" + message.getType() + "] type");
        }
    }
}
