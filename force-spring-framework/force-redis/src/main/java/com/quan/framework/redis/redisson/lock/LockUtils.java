package com.quan.framework.redis.redisson.lock;

import javax.validation.constraints.NotNull;
import java.util.concurrent.locks.Lock;
import java.util.function.Consumer;

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
