package com.quan.design.pattern.event.driven.chat;

public class UserOfflineEvent extends UserOnlineEvent {

    public UserOfflineEvent(User user) {
        super(user);
    }
}
