package com.quan.tools.builder;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 通用建造者实现
 *
 * @author Force-oneself
 * @date 2022-12-08
 */
public class Builder<T> {

    /**
     * 实例化构造工厂
     */
    private final Supplier<T> instanceFactory;

    /**
     * 初始Setter构造器
     */
    private Consumer<T> setters = obj -> {
    };


    /**
     * 私有化
     */
    private Builder(Supplier<T> instanceFactory) {
        this.instanceFactory = instanceFactory;
    }

    /**
     * 静态方法提供构造器
     *
     * @param instance 实例
     * @return 建造者
     */
    public static <T> Builder<T> of(T instance) {
        return of(() -> instance);
    }

    /**
     * 静态方法提供构造器
     *
     * @param instanceFactory 实例工厂
     * @return 建造者
     */
    public static <T> Builder<T> of(Supplier<T> instanceFactory) {
        return new Builder<>(instanceFactory);
    }


    public <R> Builder<T> with(boolean isBuild, BiConsumer<T, R> setter, R setBy) {
        return isBuild ? this.with(setter, setBy) : this;
    }

    public <R> Builder<T> with(BiConsumer<T, R> setter, R setBy) {
        return this.with(ins -> setter.accept(ins, setBy));
    }

    public Builder<T> with(Consumer<T> insConsumer) {
        setters = setters.andThen(insConsumer);
        return this;
    }

    /**
     * 建造者完成构建动作
     *
     * @return 实例
     */
    public T build() {
        T t = instanceFactory.get();
        setters.accept(t);
        return t;
    }
}
