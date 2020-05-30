package com.quan.design.pattern.latch;

import java.util.concurrent.TimeUnit;

/**
 * Latch设计模式测试类
 *
 * @author Force-Oneself
 * @date 2020-05-30
 */
public class LatchTest {

    public static void main(String[] args) throws InterruptedException {
        // 定义Latch， limit为4
        Latch latch = new CountDownLatch(4);
        new ProgrammerTravel(latch, "Alex", "Bus").start();
        new ProgrammerTravel(latch, "Gavin", "Walking").start();
        new ProgrammerTravel(latch, "Jack", "Subway").start();
        new ProgrammerTravel(latch, "Dillon", "Bicycle").start();
        // 当前（main线程会进入阻塞，直到四个程序员全部都到达目的地）
//        latch.await();
        try {
            latch.await(TimeUnit.SECONDS, 5);
            System.out.println("== all of programmer arrived ==");
        } catch (WaitTimeoutException e) {
            e.printStackTrace();
        }
    }
}
