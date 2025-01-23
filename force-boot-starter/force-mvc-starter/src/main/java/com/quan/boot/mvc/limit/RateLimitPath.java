package com.quan.boot.mvc.limit;

import java.util.Objects;

/**
 * @author Force-oneself
 * @date 2023-09-24
 */
public class RateLimitPath {

    /**
     * 限流路径
     */
    private String path;

    /**
     * 限流标识
     */
    private String key;

    /**
     * 限流时间段，单位ms
     */
    private long time =  1000;

    /**
     * 单位限流数量
     */
    private int capacity;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RateLimitPath that = (RateLimitPath) o;

        if (time != that.time) return false;
        if (capacity != that.capacity) return false;
        if (!Objects.equals(path, that.path)) return false;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + capacity;
        return result;
    }
}
