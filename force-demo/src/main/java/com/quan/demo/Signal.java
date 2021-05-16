package com.quan.demo;

public class Signal {
    private static volatile int signal = 0;

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            while (signal < 8) {
                if (signal % 3 == 0) {
                    System.out.println("threadA: " + signal);
                    signal++;
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            while (signal < 8) {
                if (signal % 3 == 1) {
                    System.out.println("threadB: " + signal);
                    signal = signal + 1;
                }
            }
        }
    }

    static class ThreadC implements Runnable {
        @Override
        public void run() {
            while (signal < 8) {
                if (signal % 3 == 2) {
                    System.out.println("threadC: " + signal);
                    signal = signal + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
        Thread.sleep(1000);
        new Thread(new ThreadC()).start();
    }
}