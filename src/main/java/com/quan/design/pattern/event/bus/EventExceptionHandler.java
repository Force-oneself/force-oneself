package com.quan.design.pattern.event.bus;

public interface EventExceptionHandler {

    void handle(Throwable cause, EventContext context);
}
