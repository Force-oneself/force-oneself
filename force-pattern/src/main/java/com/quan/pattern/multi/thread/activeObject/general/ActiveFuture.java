package com.quan.pattern.multi.thread.activeObject.general;


import com.quan.pattern.multi.thread.future.impl.FutureTask;

public class ActiveFuture<T> extends FutureTask<T> {

    @Override
    protected void finish(T result) {
        super.finish(result);
    }
}
