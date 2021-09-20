package com.quan.pattern.thread.event.bus.monitor;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

public class FileChangeEvent {

    private final Path path;
    private final WatchEvent.Kind<?> kind;

    public FileChangeEvent(Path path, WatchEvent.Kind<?> kind) {
        this.path = path;
        this.kind = kind;
    }

    public WatchEvent.Kind<?> getKind() {
        return kind;
    }

    public Path getPath() {
        return path;
    }
}
