package com.quan.pattern.multi.thread.readWrite;

/**
 * ReadLock被设计为包可见
 */
class ReadLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        // 使用Mutex作为锁
        synchronized (readWriteLock.getMutex()) {
            // 若此时有线程在进行写操作。或者有写线程在等待并且偏向写锁的标识为true时，就会无法获取读锁，只能被挂起
            while (readWriteLock.getWritingWriters() > 0
                    || (readWriteLock.getPreferWriter() && readWriteLock.getWaitingWriters() > 0)) {
                readWriteLock.getMutex().wait();
            }
            // 成功获取读锁，并且使readingReaders的数量增加
            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        // 使用Mutex作为锁，并且进行同步
        synchronized (readWriteLock.getMutex()) {
            /**
             * 释放锁的过程就是是得当前reading的数量减一
             * 将preferWriter设置为true，可以使得writer线程获得更多的机会
             * 通知唤醒与mutex关联monitor waitset中的线程
             */
            readWriteLock.decrementReadingReaders();
            readWriteLock.changePrefer(true);
            readWriteLock.getMutex().notifyAll();
        }
    }
}
