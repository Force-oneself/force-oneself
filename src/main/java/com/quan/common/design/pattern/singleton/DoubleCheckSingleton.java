package com.quan.common.design.pattern.singleton;

/**
 * Double-Check实现单例模式
 *
 * @author Force-Oneself
 * @date 2020-05-26
 */
public final class DoubleCheckSingleton {

    //实例化变量
    private byte[] data = new byte[1024];

    private static DoubleCheckSingleton instance = null;

    //私有构造函数，不允许外部new
    private DoubleCheckSingleton() {
    }

    //等到需要使用时进行创建
    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) instance = new DoubleCheckSingleton();
            }
        }
        return instance;
    }
}
