package com.quan.pattern.multi.thread.event.bus.monitor;


import com.quan.pattern.multi.thread.event.bus.Subscribe;

public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event) {
        System.out.printf("%s-%s\n", event.getPath(), event.getKind());
    }
}
