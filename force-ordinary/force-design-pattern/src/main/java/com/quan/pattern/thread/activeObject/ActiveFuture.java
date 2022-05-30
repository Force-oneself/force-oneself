package com.quan.pattern.thread.activeObject;


import com.quan.pattern.thread.future.impl.FutureTask;

public class ActiveFuture<T> extends FutureTask<T> {

    @Override
    protected void finish(T result) {
        super.finish(result);
    }
}
