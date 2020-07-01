package com.quan.design.pattern.threadPerMessage;

public class Operator {

    public void call(String business) {
        // 为每一个请求创建一个线程去处理
        TaskHandler taskHandler = new TaskHandler(new Request(business));
        new Thread(taskHandler).start();
    }

}
