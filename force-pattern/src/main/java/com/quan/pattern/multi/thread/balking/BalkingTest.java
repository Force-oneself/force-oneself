package com.quan.pattern.multi.thread.balking;

/**
 * @author Force-Oneself
 * @date 2020-05-27
 */
public class BalkingTest {

    public static void main(String[] args) {
        new DocumentEditThread("E:\\", "balking.txt").start();
    }
}
