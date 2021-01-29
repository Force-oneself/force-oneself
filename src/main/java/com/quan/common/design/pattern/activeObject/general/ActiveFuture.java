package com.quan.common.design.pattern.activeObject.general;

import com.quan.common.design.pattern.future.impl.FutureTask;

public class ActiveFuture<T> extends FutureTask<T> {

    @Override
    protected void finish(T result) {
        super.finish(result);
    }
}
