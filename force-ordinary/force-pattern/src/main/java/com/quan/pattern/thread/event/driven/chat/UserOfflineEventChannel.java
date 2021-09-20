package com.quan.pattern.thread.event.driven.chat;

import com.quan.pattern.thread.event.driven.AsyncChannel;
import com.quan.pattern.thread.event.driven.Event;

/**
 * 用户下线的Event，简单的输出用户下线即可
 */
public class UserOfflineEventChannel extends AsyncChannel {
    @Override
    protected void handle(Event message) {
        UserOfflineEvent event = (UserOfflineEvent) message;
        System.out.println("The User[" + event.getUser().getName() + "] is offline.");
    }
}
