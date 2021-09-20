package com.quan.pattern.thread.event.driven;

public class Event implements Message {

    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
