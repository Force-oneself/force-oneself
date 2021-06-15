package com.quan.common.data.structures.graph.cus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Force-oneself
 * @Description Vertex.java
 * @date 2021-06-14
 */
public class Vertex<T extends Comparable<T>> implements Serializable {

    /**
     * 顶点数据内容
     */
    private T data;

    /**
     * 出度顶点集合
     */
    private Collection<Vertex<T>> vertices;

    public Vertex(T data) {
        this.vertices = new ArrayList<>();
        this.data = data;
    }

    public boolean add(Vertex<T> vertex) {
        Objects.requireNonNull(vertex, "vertex is must not null");
        return this.vertices.add(vertex);
    }

    public boolean remove(T data) {
        Vertex<T> exists = this.vertices.stream()
                .filter(obj -> obj.getData().compareTo(data) == 0)
                .findFirst()
                .orElse(null);
        return this.vertices.remove(exists);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Collection<Vertex<T>> getVertices() {
        return vertices;
    }

    public void setVertices(Collection<Vertex<T>> vertices) {
        this.vertices = vertices;
    }
}
