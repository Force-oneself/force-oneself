package com.quan.pattern.multi.thread.future;

import java.util.concurrent.TimeUnit;

/**
 * @author Force-Oneself
 * @date 2020-05-31
 */
public class FutureTest {

    public static void main(String[] args) throws InterruptedException {
        // 定义不需要返回值的FutureService
        FutureService<Void, Void> voidService = FutureService.newService();
        Future<?> voidFuture = voidService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am finish done.");
        });
        // get方法会使当前线程进入阻塞
        voidFuture.get();

        // 定义需要返回值的FutureService
        FutureService<String, Integer> service = FutureService.newService();
        Future<Integer> future = service.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "Hello");
        // get方法会使当前线程进入阻塞
        System.out.println(future.get());

    }
}
