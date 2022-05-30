package com.quan.pattern.thread.event.driven.chat;

public class UserChatEvent extends UserOnlineEvent {

    // ChatEvent需要有聊天的信息
    private final String message;

    public UserChatEvent(User user, String message) {
        super(user);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
