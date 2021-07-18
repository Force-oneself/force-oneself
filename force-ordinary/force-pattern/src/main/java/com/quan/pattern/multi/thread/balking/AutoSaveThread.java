package com.quan.pattern.multi.thread.balking;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 下面这个类函数实现的是文档的自动保存.
 * AutoSaveThread比较简单，其主要的工作就是每个一秒的时间调用一次document的save方法
 *
 * @author Force-Oneself
 * @date 2020-05-27
 */
public class AutoSaveThread extends Thread {

    private final Document document;

    public AutoSaveThread(Document document) {
        super("DocumentAutoSaveThread");
        this.document = document;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //每隔一秒自动保存一次文档
                document.save();
                TimeUnit.SECONDS.sleep(1);
            } catch (IOException | InterruptedException e) {
                break;
            }
        }
    }
}
