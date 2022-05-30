package com.quan.pattern.thread.thread.observer;

import java.util.concurrent.TimeUnit;

public class ObservableThreadTest {

    public static void useObservable() {
        Observable observableThread = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" finished done.");
            return null;
        });
        observableThread.start();
    }

    public static void useTaskLifecycle() {
        final TaskLifecycle<String> lifecycle = new TaskLifecycle.EmptyLifecycle<String>() {
            public void onFinish(Thread thread, String result) {
                System.out.println("The result is " + result);
            }
        };
        Observable observableThread = new ObservableThread<>(lifecycle, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" finished done.");
            return "Hello Observer";
        });
        observableThread.start();
    }

    public static void main(String[] args) {
        useObservable();
        useTaskLifecycle();
    }
}
