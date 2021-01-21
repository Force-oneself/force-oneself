package com.quan.design.pattern.event.driven;

public interface Channel<E extends Message> {

    /**
     * 负责调度message
     *
     * @param message
     */
    void dispatch(E message);
}
