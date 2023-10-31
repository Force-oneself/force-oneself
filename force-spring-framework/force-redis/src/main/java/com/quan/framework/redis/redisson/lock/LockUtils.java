package com.quan.framework.redis.redisson.lock;

import java.util.concurrent.locks.Lock;

/**
 * LockUtils
 *
 * @author Force-oneself
 * @date 2022-12-18
 */
public class LockUtils {

    public static void lock(Lock lock, Runnable runnable) {
        lock.lock();
        try {
            runnable.run();
        } finally {
            lock.unlock();
        }
    }
}
