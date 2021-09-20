package com.quan.pattern.thread.activeObject.general;


import com.quan.pattern.thread.future.Future;

import java.lang.reflect.Method;

/**
 * 设计为包可见，ActiveMessage只在框架内部使用，不会对外暴露
 */
class ActiveMessage {

    // 接口方法的参数
    private final Object[] objects;
    // 接口方法
    private final Method method;
    // 有返回值的方法，会返回ActiveFuture<?>类型
    private final ActiveFuture<Object> future;
    // 具体的Service的接口
    private final Object service;

    // 构造ActiveMessage是由Builder来完成的
    public ActiveMessage(Builder builder) {
        this.objects = builder.objects;
        this.method = builder.method;
        this.future = builder.future;
        this.service = builder.service;
    }

    // ActiveMessage的方法通过反射的方式调用执行的具体实现
    public void execute() {
        try {
            // 执行接口的方法
            Object result = method.invoke(service, objects);
            if (future != null) {
                // 如果是有返回值的接口方法，则需要通过get方法获得最终的结果
                Future<?> realFuture = (Future<?>) result;
                Object realResult = realFuture.get();
                // 将结果交给ActiveFuture，接口方法的线程会得到返回
                future.finish(realResult);
            }
        } catch (Exception e) {
            if (future != null) {
                future.finish(null);
            }
        }
    }

    // 主要负责对ActiveMessage的构建，是一种典型的Gof Builder设计模式
    static class Builder {
        private Object[] objects;
        private Method method;
        private ActiveFuture<Object> future;
        private Object service;

        public Builder useMethod(Method method) {
            this.method = method;
            return this;
        }

        public Builder returnFuture(ActiveFuture<Object> future) {
            this.future = future;
            return this;
        }

        public Builder withObjects(Object[] objects) {
            this.objects = objects;
            return this;
        }

        public Builder forService(Object object) {
            this.service = service;
            return this;
        }

        // 构建ActiveMessage实例
        public ActiveMessage build() {
            return new ActiveMessage(this);
        }

    }

}
