package com.quan.pattern.thread.event.bus;

public interface EventExceptionHandler {

    void handle(Throwable cause, EventContext context);
}
