package com.quan.framework.redis.redisson.lock;

/**
 * 锁服务接口
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
}
