package com.quan.pattern.thread.singleton;

/**
 * 使用枚举+Holder实现单例模式
 *
 * @author Force-Oneself
 * @date 2020-05-26
 */
public final class EnumAndHolderSingleton {

    //实例化变量
    private byte[] data = new byte[1024];

    private EnumAndHolderSingleton() {
    }

    private enum EnumHolder {
        INSTANCE;
        private EnumAndHolderSingleton instance;

        EnumHolder() {
            this.instance = new EnumAndHolderSingleton();
        }

        private EnumAndHolderSingleton getEnumAndHolderSingleton() {
            return instance;
        }

    }

    public static EnumAndHolderSingleton getInstance() {
        return EnumHolder.INSTANCE.getEnumAndHolderSingleton();
    }
}
