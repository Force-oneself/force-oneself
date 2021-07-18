package com.quan.pattern.multi.thread.event.driven.chat;

public class UserOfflineEvent extends UserOnlineEvent {

    public UserOfflineEvent(User user) {
        super(user);
    }
}
