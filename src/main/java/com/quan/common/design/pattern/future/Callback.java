package com.quan.common.design.pattern.future;

/**
 * 回调函数接口
 *
 * @author Force-Oneself
 * @date 2020-05-31
 */
@FunctionalInterface
public interface Callback<T> {

    // 任务完成后会调用该方法，其中T为任务执行后的结果
    void call(T t);
}
