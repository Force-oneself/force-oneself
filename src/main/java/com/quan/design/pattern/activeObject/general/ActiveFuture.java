package com.quan.design.pattern.activeObject.general;

import com.quan.design.pattern.future.impl.FutureTask;

public class ActiveFuture<T> extends FutureTask<T> {

    @Override
    protected void finish(T result) {
        super.finish(result);
    }
}
