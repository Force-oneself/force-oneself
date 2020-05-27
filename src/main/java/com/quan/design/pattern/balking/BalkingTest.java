package com.quan.design.pattern.balking;

/**
 * @author Force-Oneself
 * @date 2020-05-27
 */
public class BalkingTest {

    public static void main(String[] args) {
        new DocumentEditThread("E:\\", "balking.txt").start();
    }
}
