package com.quan.design.pattern.activeObject;

import com.quan.design.pattern.future.Future;
import com.quan.design.pattern.future.FutureService;

import java.util.concurrent.TimeUnit;

public class OrderServiceImpl implements OrderService {

    @Override
    public Future<String> findOrderDetails(long orderId) {
        return FutureService.<Long, String>newService().submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("process the orderID ->" + orderId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "The order Details Information";
        }, orderId, null);
    }

    @Override
    public void order(String account, long orderId) {
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("process the order for account " + account + ",orderId " + orderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
