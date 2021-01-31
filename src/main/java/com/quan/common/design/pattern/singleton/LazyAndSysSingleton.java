package com.quan.common.design.pattern.singleton;

/**
 * 懒汉+同步实现单例模式
 *
 * @author Force-Oneself
 * @date 2020-05-26
 */
public final class LazyAndSysSingleton {

    //实例化变量
    private byte[] data = new byte[1024];

    private static LazyAndSysSingleton instance = null;

    //私有构造函数，不允许外部new
    private LazyAndSysSingleton() {
    }

    //等到需要使用时进行创建
    public static synchronized LazyAndSysSingleton getInstance() {
        if (instance == null) {
            instance = new LazyAndSysSingleton();
        }
        return instance;
    }
}
