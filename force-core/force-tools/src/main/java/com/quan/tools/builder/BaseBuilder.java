package com.quan.tools.builder;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author Force-oneself
 * @date 2023-09-21
 */
public interface BaseBuilder<T, C extends BaseBuilder<T,C>> {

    default <R> C with(BiConsumer<T, R> setter, R setBy) {
        return this.with(ins -> setter.accept(ins, setBy));
    }

    C with(Consumer<T> insConsumer);

    T build();


    interface Builder<T> extends BaseBuilder<T, Builder<T>> {

    }
}
