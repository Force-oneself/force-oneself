package com.quan.design.pattern.lock.readWrite;

public interface ReadWriteLock {

    // 创建reader锁
    Lock readLock();

    // 创建write锁
    Lock writeLock();

    // 获取当前有多少线程正在执行写操作
    int getWritingWriters();

    // 获取当前有多少线程正在等待获取写入锁
    int getWaitingWriters();

    // 获取当前有多少线程正在等待获取reader锁
    int getReadingReaders();

    // 工程方法，创建ReadWriteLock
    static ReadWriteLock readWriteLock() {
        return new ReadWriteLockImpl();
    }

    // 工厂方法，创建ReadWriteLock，并且传入preferWrite
    static ReadWriteLock readWriteLock(boolean preferWriter) {
        return new ReadWriteLockImpl(preferWriter);
    }
}
