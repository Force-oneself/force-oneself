package com.quan.common.design.pattern.event.bus.monitor;

import com.quan.common.design.pattern.event.bus.Subscribe;

public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event) {
        System.out.printf("%s-%s\n", event.getPath(), event.getKind());
    }
}
