package com.quan.common.data.structures.graph.cus;

import java.io.Serializable;

/**
 * @author Force-oneself
 * @Description Edge.java
 * @date 2021-06-14
 */
public class Edge<V> implements Serializable {

    /**
     * 出度
     */
    private V from;

    /**
     * 入度
     */
    private V to;

    public Edge() {}

    public Edge(V from, V to) {
        this.from = from;
        this.to = to;
    }

    public V getFrom() {
        return from;
    }

    public void setFrom(V from) {
        this.from = from;
    }

    public V getTo() {
        return to;
    }

    public void setTo(V to) {
        this.to = to;
    }
}
