package com.quan.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * RedisTest
 *
 * @author Force-oneself
 * @date 2022-11-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisTest.class)
public class RedisTest {


    public static RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setDatabase(0)
                .setAddress("redis://106.53.49.229:19097");
        return Redisson.create(config);
    }


    public static void main(String[] args) throws InterruptedException {
        RLock lock = redissonClient().getLock("lock");

            try {
                if (!lock.tryLock(10, TimeUnit.SECONDS)) {
                    System.out.println("获取锁失败");
                }
                System.err.println("线程id：" + Thread.currentThread().getId());
                //Thread.sleep(TimeUnit.SECONDS.toMillis(20));
                Thread.currentThread().interrupt();
            } finally {
                if (lock.isLocked()) {

                    lock.unlock();
                }
            }
        System.err.println("线程id：" + Thread.currentThread().getId());
        if (Thread.currentThread().isInterrupted()) {
            System.err.println("当前线程已中断");
        }
        //  ttl lock
        while (true) {
        }

    }
}
