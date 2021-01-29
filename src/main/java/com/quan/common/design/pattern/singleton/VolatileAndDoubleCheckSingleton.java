package com.quan.common.design.pattern.singleton;

/**
 * Volatile+Double-Check实现单例模式
 *
 * @author Force-Oneself
 * @date 2020-05-26
 */
public final class VolatileAndDoubleCheckSingleton {

    //实例化变量
    private byte[] data = new byte[1024];

    //禁止指令重排序
    private volatile static VolatileAndDoubleCheckSingleton instance = null;

    //私有构造函数，不允许外部new
    private VolatileAndDoubleCheckSingleton() {
    }

    //等到需要使用时进行创建
    public static VolatileAndDoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (VolatileAndDoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new VolatileAndDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
