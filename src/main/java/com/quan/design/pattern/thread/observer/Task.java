package com.quan.design.pattern.thread.observer;

@FunctionalInterface
public interface Task<T> {

    // 任务执行接口，该接口允许有返回值
    T call();
}
