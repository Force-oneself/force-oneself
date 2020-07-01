package com.quan.design.pattern.event.driven.chat;

import com.quan.design.pattern.event.driven.Event;

public class UserOnlineEvent extends Event {

    private final User user;

    public UserOnlineEvent(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
