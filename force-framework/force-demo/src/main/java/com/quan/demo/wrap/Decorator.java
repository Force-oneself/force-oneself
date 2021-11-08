package com.quan.demo.wrap;

import java.util.function.Supplier;

/**
 * @author Force-oneself
 * @Description Decorator
 * @date 2021-11-03
 */
public interface Decorator<T> {

    // 被装饰的动作 - 原材料
    // 装饰动作 - 接口
    // 触发动作 - 执行

    Supplier<T> decorate();


    Supplier<T> source();

}
