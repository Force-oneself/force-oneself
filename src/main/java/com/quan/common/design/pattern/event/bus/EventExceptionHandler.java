package com.quan.common.design.pattern.event.bus;

public interface EventExceptionHandler {

    void handle(Throwable cause, EventContext context);
}
