package com.quan.common.design.pattern.lock.readWrite;

import static java.lang.Thread.currentThread;

public class ReadWriteLockTest {

    private final static String TEXT = "this is the example for read write lock";

    public static void main(String[] args) {
        // 定义共享数据
        final ShareData shareData = new ShareData(5);
        // 定义2两个线程进行写操作
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int index = 0; index < TEXT.length(); index++) {
                    try {
                        char c = TEXT.charAt(index);
                        shareData.write(c);
                        System.out.println(currentThread() + " write " + c);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        // 创建10个线程进行数据读操作
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(currentThread() + " read " + new String(shareData.read()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }
}
