package com.quan.pattern.multi.thread.singleton;

/**
 * 饿汉实现单例模式
 *
 * @author Force-Oneself
 * @date 2020-05-26
 */
public final class EagerSingleton {

    //实例化变量
    private byte[] data = new byte[1024];

    //直接初始化
    private static EagerSingleton instance = new EagerSingleton();

    //私有构造函数，不允许外部new
    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
