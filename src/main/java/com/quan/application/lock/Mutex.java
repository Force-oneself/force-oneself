package com.quan.application.lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description: Mutex是一个不可重入的互斥锁实现。锁资源（AQS里的state）只有两种状态：0表示未锁定，1表示锁定。
 * @Author heyq
 * @Date 2021-04-04
 **/
public class Mutex implements Lock, Serializable {

    // 自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        // 判断是否锁定状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }

        // 尝试获取资源，立即返回。成功则返回true，否则false。
        @Override
        public boolean tryAcquire(int acquires) {
            // 这里限定只能为1个量
            assert acquires == 1;
            // state为0才设置为1，不可重入！
            if (compareAndSetState(0, 1)) {
                // 设置为当前线程独占资源
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 尝试释放资源，立即返回。成功则为true，否则false。
        @Override
        protected boolean tryRelease(int releases) {
            // 限定为1个量
            assert releases == 1;
            // 既然来释放，那肯定就是已占有状态了。只是为了保险，多层判断！
            if (getState() == 0)
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            // 释放资源，放弃占有状态
            setState(0);
            return true;
        }
    }

    // 真正同步类的实现都依赖继承于AQS的自定义同步器！
    private final Sync sync = new Sync();

    // lock<-->acquire。两者语义一样：获取资源，即便等待，直到成功才返回。
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    // tryLock<-->tryAcquire。两者语义一样：尝试获取资源，要求立即返回。成功则为true，失败则为false。
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.convert(time, unit));

    }
    // unlock<-->release。两者语文一样：释放资源。
    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    // 锁是否占有状态
    public boolean isLocked() {
        return sync.isHeldExclusively();
    }
}
