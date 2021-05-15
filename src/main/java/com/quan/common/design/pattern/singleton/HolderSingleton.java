package com.quan.common.design.pattern.singleton;

/**
 * Holder实现单例模式
 *
 * @author Force-Oneself
 * @date 2020-05-26
 */
public final class HolderSingleton {

    //实例化变量
    private byte[] data = new byte[1024];

    //私有构造函数，不允许外部new
    private HolderSingleton() {
    }

    //在静态内部类中持有singleton的实例，可以被直接初始化
    private static class Holder {
        private static HolderSingleton instance = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return Holder.instance;
    }
}
