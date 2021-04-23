package com.quan.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-01-14
 **/
public class ThreadPoolDemo {

    /**
     * 1、newSingleThreadExecutor
     *
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     *
     * 2、newFixedThreadPool
     *
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     *
     * 3、newScheduledThreadPool
     *
     * 创建一个可定期或者延时执行任务的定长线程池，支持定时及周期性任务执行。
     *
     * 4、newCachedThreadPoo
     *
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * @param args
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                10,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(200),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


    }
}
