package com.quan.lock;

import com.quan.tools.util.LockUtils;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁服务接口
 *
 * @author Force-oneself
 * @date 2024-12-07
 */
public interface LockService {

    /**
     * 获取分布式锁
     *
     * @param lockName 锁名称
     * @return 分布式锁
     */
    DistributedLock getLock(String lockName);

    /**
     * 使用锁
     *
     * @param lockName 锁名称
     * @param runnable 使用锁的代码
     */
    default void lock(String lockName, Runnable runnable) {
        LockUtils.lock(this.getLock(lockName), runnable);
    }

    /**
     * 使用锁
     *
     * @param lockName 锁名称
     * @param runnable 使用锁的代码
     */
    default void tryLock(String lockName, Runnable runnable) {
        LockUtils.tryLock(this.getLock(lockName), runnable);
    }

    /**
     * 使用锁, 等待设置时间就返回
     *
     * @param time     时间
     * @param unit     时间单位
     * @param lockName 锁名称
     * @param runnable 使用锁的代码
     * @throws InterruptedException 中断异常
     */
    default void tryLock(long time, TimeUnit unit, String lockName, Runnable runnable) throws InterruptedException {
        DistributedLock lock = this.getLock(lockName);
        if (lock.tryLock(time, unit)) {
            try {
                runnable.run();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 解锁
     *
     * @param lockName 锁名称
     */
    default void unLock(String lockName) {
        this.getLock(lockName).unlock();
    }
}
