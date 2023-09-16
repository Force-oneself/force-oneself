package com.quan.demo.concurrency;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Force-oneself
 * @date 2023-02-28
 */
public class BufferDemo {

    public static void main(String[] args) throws InterruptedException {
        MemoryBuffer<String> buffer = new DoubleBuffer<>(10);
        for (int i = 0; i < 1; i++) {
            circulate(buffer);
        }
        System.out.println("end -------");
    }

    private static void circulate(MemoryBuffer<String> buffer) throws InterruptedException {
        int threads = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        List<Thread> threadList = IntStream.generate(() -> 0)
                .limit(threads)
                .mapToObj(i -> new Thread(() -> {
                    for (int j = 0; j < 100; j++) {
                        buffer.write("1");
                    }
                    countDownLatch.countDown();
                }))
                .collect(Collectors.toList());
        threadList.forEach(Thread::start);

        countDownLatch.await();
    }
}
