package com.quan.pattern.thread.event.driven;

public interface Message {

    /**
     * 获取Message的类型
     *
     * @return
     */
    Class<? extends Message> getType();
}
