package com.quan.lock.support;

import com.quan.lock.DistributedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.lang.NonNull;

import java.util.concurrent.TimeUnit;

/**
 * 使用Redisson实现的分布式锁
 *
 * @author Force-oneself
 * @date 2022-08-10
 */
public class RedissonDistributedLock implements DistributedLock {

    private final RedissonClient redissonClient;
    private final String lockKey;

    public RedissonDistributedLock(RedissonClient redissonClient, String lockKey) {
        this.redissonClient = redissonClient;
        this.lockKey = lockKey;
    }

    @Override
    public void lock() {
        RLock lock = this.getLock();
        // 是否线程重入，是则不需要重新加锁，否则需要加锁
        if (!lock.isHeldByCurrentThread()) {
            lock.lock();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        RLock lock = this.getLock();
        lock.lockInterruptibly();
    }

    @Override
    public boolean tryLock() {
        RLock lock = this.getLock();
        // 是否线程重入，是则可以解锁
        return lock.isHeldByCurrentThread() || lock.tryLock();
    }

    @Override
    public boolean tryLock(long time, @NonNull TimeUnit unit) throws InterruptedException {
        RLock lock = this.getLock();
        // 是否线程重入，是则可以解锁
        return lock.isHeldByCurrentThread() || lock.tryLock(time, unit);
    }

    @Override
    public void unlock() {
        RLock lock = this.getLock();
        // 是否线程重入，是则可以解锁
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    private RLock getLock() {
        return redissonClient.getLock(lockKey);
    }
}
