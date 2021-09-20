package com.quan.pattern.thread.future.impl;

import com.quan.pattern.thread.future.Callback;
import com.quan.pattern.thread.future.Future;
import com.quan.pattern.thread.future.FutureService;
import com.quan.pattern.thread.future.Task;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Force-Oneself
 * @date 2020-05-30
 */
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    // 为执行的线程之低昂名字前缀
    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";

    private final AtomicInteger nextCounter = new AtomicInteger(0);

    private String getNextName() {
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> future = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            // 任务执行结束之后将null作为结果传给future
            future.finish(null);
        }, getNextName()).start();
        return future;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        final FutureTask<OUT> future = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            // 任务执行结束之后将result作为结果传给future
            future.finish(result);
        }, getNextName()).start();
        return future;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback) {

        final FutureTask<OUT> future = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            future.finish(result);
            // 执行接口回调
            if (null != callback) {
                callback.call(result);
            }
        }, getNextName()).start();
        return future;
    }
}
