package com.quan.common.design.pattern.guardedsuspension;

import java.util.LinkedList;

/**
 * Guarded Suspension (确保挂起)设计模式
 *
 * @author Force-Oneself
 * @date 2020-05-31
 */
public class GuardedSuspension {

    // 定义存放Integer类型的queue
    private final LinkedList<Integer> queue = new LinkedList<>();

    // 定义queue的最大容量为100
    private final int LIMIT = 100;

    // 往queue中插入数据，如果queue中的元素超过了最大容量，则会陷入阻塞
    public void offer(Integer data) throws InterruptedException {
        synchronized (this) {
            // 判断queue的当前元素是否超过了LIMIT
            while (queue.size() >= LIMIT) {
                this.wait();
            }
            // 插入元素并且唤醒take线程
            queue.addLast(data);
            this.notifyAll();
        }
    }

    // 从队列中获取元素，如果队列此时为空，则会使当前线程阻塞
    public Integer take() throws InterruptedException {
        synchronized (this) {
            // 如果队列为空
            while (queue.isEmpty()) {
                this.wait();
            }
            // 通知offer线程可以继续插入数据了
            this.notifyAll();
            return queue.removeFirst();
        }
    }

}
