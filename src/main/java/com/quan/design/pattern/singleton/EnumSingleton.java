package com.quan.design.pattern.singleton;

/**
 * 枚举实现单例模式
 *
 * @author Force-Oneself
 * @date 2020-05-26
 */
public enum EnumSingleton {

    INSTANCE;
    private byte[] data = new byte[1024];

    EnumSingleton() {
        System.out.println("INSTANCE will be initialized immediately");
    }

    public static void method() {
        //调用该方法会主动使用Singleton,INSTANCE实例将会被实例化
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
