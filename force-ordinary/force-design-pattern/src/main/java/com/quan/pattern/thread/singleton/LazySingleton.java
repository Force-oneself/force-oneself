package com.quan.pattern.thread.singleton;

/**
 * 懒汉式实现单例模式
 *
 * @author Force-Oneself
 * @date 2020-05-26
 */
public final class LazySingleton {

    //实例化变量
    private byte[] data = new byte[1024];

    private static LazySingleton instance = null;

    //私有构造函数，不允许外部new
    private LazySingleton() {
    }

    //等到需要使用时进行创建
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
