package com.quan.framework.redis.redisson.lock;

import java.util.concurrent.locks.Lock;
import java.util.function.Supplier;

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

    public static <T> T lock(Lock lock, Supplier<T> supplier) {
        lock.lock();
        try {
            return supplier.get();
        } finally {
            lock.unlock();
        }
    }

    public static void tryLock(Lock lock, Runnable runnable) {
        if (lock.tryLock()) {
            try {
                runnable.run();
            } finally {
                lock.unlock();
            }
        }
    }

    public static <T> T tryLock(Lock lock, Supplier<T> supplier) {
        if (lock.tryLock()) {
            try {
                return supplier.get();
            } finally {
                lock.unlock();
            }
        }
        return null;
    }
}
