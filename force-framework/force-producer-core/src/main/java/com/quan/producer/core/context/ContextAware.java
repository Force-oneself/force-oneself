package com.quan.producer.core.context;

/**
 * @author Force-oneself
 * @description ContextAware
 * @date 2021-11-22
 **/
public interface ContextAware<T> {

    /**
     * 上下文回调接口
     *
     * @param context 上下文
     */
    void setContext(Context<T> context);
}
