package com.quan.pattern.multi.thread.event.driven.chat;

import com.quan.common.design.pattern.event.driven.AsyncEventDispatcher;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ThreadLocalRandom.current;

public class UserChatThread extends Thread {

    private final User user;

    private final AsyncEventDispatcher dispatcher;

    public UserChatThread(User user, AsyncEventDispatcher dispatcher) {
        super(user.getName());
        this.user = user;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        try {
            // user上线，发送Online Event
            dispatcher.dispatch(new UserOnlineEvent(user));
            for (int i = 0; i < 5; i++) {
                // 发送User的聊天信息
                dispatcher.dispatch(new UserChatEvent(user, getName() + "-Hello-" + i));
                // 短暂休眠1-10秒
                TimeUnit.SECONDS.sleep(current().nextInt(10));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // User 下线，发送Offline Event
            dispatcher.dispatch(new UserOfflineEvent(user));
        }
    }
}
