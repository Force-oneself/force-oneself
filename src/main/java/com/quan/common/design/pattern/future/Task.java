package com.quan.common.design.pattern.future;

/**
 * Task接口
 *
 * @author Force-Oneself
 * @date 2020-05-30
 */
@FunctionalInterface
public interface Task<IN, OUT> {

    // 给定一个参数，经过计算返回结果
    OUT get(IN input);
}
