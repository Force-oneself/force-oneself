package com.quan.flink.event;

public class ClickEvent {
    public String userId;
    public int count;
    public long timestamp;

    public ClickEvent() {
        this(null, 0);
    }

    public ClickEvent(String userId, int count) {
        this.userId = userId;
        this.count = count;
        this.timestamp = System.nanoTime();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ClickEvent{" +
                "userId='" + userId + '\'' +
                ", count=" + count +
                ", timestamp=" + timestamp +
                '}';
    }
}
