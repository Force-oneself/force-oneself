package com.quan.pattern.thread.event.driven.chat;

public class UserOfflineEvent extends UserOnlineEvent {

    public UserOfflineEvent(User user) {
        super(user);
    }
}
