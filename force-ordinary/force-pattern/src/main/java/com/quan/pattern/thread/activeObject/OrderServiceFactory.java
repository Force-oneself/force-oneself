package com.quan.pattern.thread.activeObject;

public final class OrderServiceFactory {
    // 将ActiveMessageQueue定义static的目的是，保持其在整个JVM进程中是唯一的，并且ActiveDaemonThread会在此刻启动
    private final static ActiveMessageQueue ACTIVE_MESSAGE_QUEUE = new ActiveMessageQueue();

    // 不予许外部通过new的方式构建
    private OrderServiceFactory() {
    }

    // 返回OrderServiceProxy
    public static OrderService toActiveObject(OrderService orderService) {
        return new OrderServiceProxy(orderService, ACTIVE_MESSAGE_QUEUE);
    }

}
