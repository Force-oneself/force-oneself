package com.quan.lock.support;

import com.quan.lock.DistributedLock;
import com.quan.lock.LockService;
import org.redisson.api.RedissonClient;

/**
 * Redisson 实现分布式锁服务
 *
 * @author Force-oneself
 * @date 2024-12-25
 */
public class RedissonLockService implements LockService {

    /**
     * Redisson客户端
     */
    private final RedissonClient redissonClient;

    public RedissonLockService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public DistributedLock getLock(String lockName) {
        // 使用Redisson实现的分布式锁
        return new RedissonDistributedLock(redissonClient, lockName);
    }
}
