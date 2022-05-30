package com.quan.pattern.thread.latch;

import java.util.concurrent.TimeUnit;

/**
 * 门阀实现子类
 *
 * @author Force-Oneself
 * @date 2020-05-30
 */
public class CountDownLatch extends Latch {


    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            // 当limit>0时，当前线程进入阻塞状态
            while (limit > 0) {
                this.await();
            }
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if (limit <= 0) {
                throw new IllegalStateException("all of task already arrived");
            }
            // 使limit减一，并且通知阻塞线程
            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getNotArrived() {
        // 返回有多少线程还未完成任务
        return limit;
    }

    @Override
    public void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutException {
        if (time <= 0) {
            throw new IllegalArgumentException("the time is invalid.");
        }
        long remainingNanos = unit.toNanos(time);
        // 等待任务将endNanos纳秒后超时
        final long endNanos = System.nanoTime() + remainingNanos;
        synchronized (this) {
            while (limit > 0) {
                // 如果超时则抛出WaitTimeException异常
                if (TimeUnit.NANOSECONDS.toMillis(remainingNanos) <= 0) {
                    throw new WaitTimeoutException("the wait time over specify time.");
                }
                // 等待remainingNanos, 在等待的过程中有可能会被中断，需要重新计算remainingNanos
                this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
                remainingNanos = endNanos - System.nanoTime();
            }
        }
    }
}
