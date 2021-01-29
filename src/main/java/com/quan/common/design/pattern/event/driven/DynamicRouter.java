package com.quan.common.design.pattern.event.driven;

public interface DynamicRouter<E extends Message> {

    /**
     * 针对每一种message类型注册相关的Channel，只有找到合适的Channel该Message才会被处理
     *
     * @param messageType
     */
    void registerChannel(Class<? extends E> messageType, Channel<? extends E> channel);

    /**
     * 为相应的Channel分配Message
     *
     * @param message
     */
    void dispatch(E message);
}
