package com.quan.common.design.pattern.event.driven;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AsyncEventDispatcher implements DynamicRouter<Event> {

    // 使用线程安全的ConcurrentHashMap替换HashMap
    private final Map<Class<? extends Event>, AsyncChannel> routerTable;

    public AsyncEventDispatcher() {
        this.routerTable = new ConcurrentHashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Event> messageType, Channel<? extends Event> channel) {
        // 在AsyncEventDispatch中，Channel必须是AsyncChannel类型
        if (!(channel instanceof AsyncChannel)) {
            throw new IllegalArgumentException("The channel must be AsyncChannelType.");
        }
        this.routerTable.put(messageType, (AsyncChannel) channel);
    }

    @Override
    public void dispatch(Event message) {
        if (routerTable.containsKey(message.getType())) {
            routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatcherException("Can't match the channel for [" + message.getType() + "] type");
        }
    }

    public void shutdown() {
        // 关闭所有的Channel以释放资源
        routerTable.values().forEach(AsyncChannel::stop);
    }
}
