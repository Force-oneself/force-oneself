package com.quan.design.pattern.activeObject;

import static java.lang.Thread.currentThread;

public class ActiveObjectTest {

    public static void main(String[] args) throws InterruptedException {
        // 在创建OrderService时需要传递OrderService接口的具体实现
        OrderService orderService = OrderServiceFactory.toActiveObject(new OrderServiceImpl());
        orderService.order("hello", 453453);
        // 立即返回
        System.out.println("Return immediately");
        currentThread().join();
    }
}
