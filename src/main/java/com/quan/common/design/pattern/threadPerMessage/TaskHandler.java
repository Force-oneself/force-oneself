package com.quan.common.design.pattern.threadPerMessage;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * TaskHandler用于处理每一个提交的Request请求，由于TaskHandler将被Thread执行，
 * 因此需要实现Runnable接口
 */
public class TaskHandler implements Runnable {

    // 需要处理的Request请求
    private final Request request;

    public TaskHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        System.out.println("Begin handle " + request);
        slowly();
        System.out.println("End bandle " + request);
    }

    private void slowly() {
        try {
            TimeUnit.SECONDS.sleep(current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
