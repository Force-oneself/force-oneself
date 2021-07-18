package com.quan.pattern.multi.thread.event.bus;

public interface EventExceptionHandler {

    void handle(Throwable cause, EventContext context);
}
