package com.quan.common.design.pattern.event.driven.chat;

import com.quan.common.design.pattern.event.driven.AsyncEventDispatcher;

public class UserChatApplication {

    public static void main(String[] args) {
        // 定义异步的Router
        final AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();
        // 为Router注册Channel和Event之间的关系
        dispatcher.registerChannel(UserOnlineEvent.class, new UserChatEventChannel());
        dispatcher.registerChannel(UserOfflineEvent.class, new UserOfflineEventChannel());
        dispatcher.registerChannel(UserChatEvent.class, new UserChatEventChannel());
        // 启动三个登录聊天室的User
        new UserChatThread(new User("Leo"), dispatcher).start();
        new UserChatThread(new User("Alex"), dispatcher).start();
        new UserChatThread(new User("Tina"), dispatcher).start();
    }
}
