package com.quan.design.pattern.event.driven;

public class Event implements Message {

    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
