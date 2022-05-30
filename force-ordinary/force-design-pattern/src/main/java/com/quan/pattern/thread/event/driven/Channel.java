package com.quan.pattern.thread.event.driven;

public interface Channel<E extends Message> {

    /**
     * 负责调度message
     *
     * @param message
     */
    void dispatch(E message);
}
