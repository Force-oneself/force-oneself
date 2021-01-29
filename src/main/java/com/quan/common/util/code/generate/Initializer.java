package com.quan.common.util.code.generate;

/**
 * @Description: 初始化器
 * @Author heyq
 * @Date 2021-01-01
 **/
@FunctionalInterface
public interface Initializer<T> {

    void init(T t);
}
