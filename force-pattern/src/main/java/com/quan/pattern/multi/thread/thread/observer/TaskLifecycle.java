package com.quan.pattern.multi.thread.thread.observer;

public interface TaskLifecycle<T> {

    // 任务启动时触发
    void onStart(Thread thread);

    // 任务运行时触发
    void onRunning(Thread thread);

    // 任务完成时触发
    void onFinish(Thread thread, T result);

    // 任务报错时触发
    void onError(Thread thread, Exception e);

    class EmptyLifecycle<T> implements TaskLifecycle<T> {

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}
