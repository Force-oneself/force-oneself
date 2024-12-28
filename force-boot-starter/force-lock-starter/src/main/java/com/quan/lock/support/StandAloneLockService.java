package com.quan.lock.support;

import com.quan.lock.DistributedLock;
import com.quan.lock.LockService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单机版锁服务实现
 *
 * @author Force-oneself
 * @date 2024-12-25
 */
public class StandAloneLockService implements LockService {

    private final Map<String, DistributedLock> lockMap = new ConcurrentHashMap<>();

    @Override
    public DistributedLock getLock(String lockName) {
        return lockMap.computeIfAbsent(lockName, k -> new StandAloneDistributedLock());
    }

    @Override
    public void unLock(String lockName) {
        LockService.super.unLock(lockName);
        // 释放锁进行删除key
        lockMap.remove(lockName);
    }
}
