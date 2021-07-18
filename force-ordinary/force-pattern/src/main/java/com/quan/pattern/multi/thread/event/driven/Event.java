package com.quan.pattern.multi.thread.event.driven;

public class Event implements Message {

    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
