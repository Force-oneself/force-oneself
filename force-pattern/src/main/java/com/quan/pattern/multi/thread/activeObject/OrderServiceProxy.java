package com.quan.pattern.multi.thread.activeObject;


import com.quan.pattern.multi.thread.future.Future;

import java.util.HashMap;
import java.util.Map;

public class OrderServiceProxy implements OrderService {

    private final OrderService orderService;
    private final ActiveMessageQueue activeMessageQueue;

    public OrderServiceProxy(OrderService orderService, ActiveMessageQueue activeMessageQueue) {
        this.orderService = orderService;
        this.activeMessageQueue = activeMessageQueue;
    }

    @Override
    public Future<String> findOrderDetails(long orderId) {
        // 定义一个ActiveFuture，并且可支持立即返回
        final ActiveFuture<String> activeFuture = new ActiveFuture<>();
        // 手机方法入参以及返回的ActiveFuture封装成MethodMessage
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("activeFuture", activeFuture);
        MethodMessage message = new FindOrderDetailsMessage(params, orderService);
        // 将MethodMessage保存至activeMessageQueue中
        activeMessageQueue.offer(message);
        return activeFuture;
    }

    @Override
    public void order(String account, long orderId) {
        // 收集方法参数，并且封装成MethodMessage，然后offer至队列中
        Map<String, Object> params = new HashMap<>();
        params.put("account", account);
        params.put("orderId", orderId);
        MethodMessage message = new OrderMessage(params, orderService);
        activeMessageQueue.offer(message);
    }
}
