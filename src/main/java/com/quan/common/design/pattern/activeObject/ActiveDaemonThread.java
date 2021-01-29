package com.quan.common.design.pattern.activeObject;

public class ActiveDaemonThread extends Thread {

    private final ActiveMessageQueue queue;

    public ActiveDaemonThread(ActiveMessageQueue queue) {
        super("ActiveDaemonThread");
        this.queue = queue;
        // 设置为守护线程
        setDaemon(true);
    }

    @Override
    public void run() {
        for (; ; ) {
            // 从MethodMessage队列中获取一个MethodMessage，然后执行execute方法
            MethodMessage methodMessage = this.queue.take();
            methodMessage.execute();
        }
    }
}
