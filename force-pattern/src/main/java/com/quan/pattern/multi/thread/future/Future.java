package com.quan.pattern.multi.thread.future;

/**
 * Future接口
 *
 * @author Force-Oneself
 * @date 2020-05-30
 */
public interface Future<T> {

    // 返回计算后的结果，该方法会陷入阻塞状态
    T get() throws InterruptedException;

    // 判断任务是否已经完成
    boolean done();
}
