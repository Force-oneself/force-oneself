package com.quan.pattern.thread.activeObject;

import com.quan.pattern.thread.activeObject.general.ActiveMethod;
import com.quan.pattern.thread.future.Future;
import com.quan.pattern.thread.future.FutureService;

import java.util.concurrent.TimeUnit;

public class OrderServiceImpl implements OrderService {

    @ActiveMethod
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

    @ActiveMethod
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
