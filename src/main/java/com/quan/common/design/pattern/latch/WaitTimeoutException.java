package com.quan.common.design.pattern.latch;

/**
 * 当子任务线程执行超时的时候将会抛出该异常
 *
 * @author Force-Oneself
 * @date 2020-05-30
 */
public class WaitTimeoutException extends Exception {

    public WaitTimeoutException(String message) {
        super(message);
    }
}
