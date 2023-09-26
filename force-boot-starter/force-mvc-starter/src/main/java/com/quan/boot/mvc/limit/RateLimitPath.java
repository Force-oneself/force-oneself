package com.quan.boot.mvc.limit;

import com.google.common.base.Objects;

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
    private long time;

    /**
     * 单位限流数量
     */
    private long flow;

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

    public long getFlow() {
        return flow;
    }

    public void setFlow(long flow) {
        this.flow = flow;
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
        return time == that.time && flow == that.flow && Objects.equal(path, that.path) && Objects.equal(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(path, key, time, flow);
    }
}
