package com.quan.framework.mongo.push.iterator;

import com.quan.framework.mongo.push.context.Context;

import java.io.Closeable;
import java.util.Iterator;

/**
 * @author Force-oneself
 * @Description PushIterator
 * @date 2021-09-15
 */
public interface IteratorAdapter<T> extends Closeable, Iterator<T> {

    default boolean support(Context context) {
        return false;
    }
}
