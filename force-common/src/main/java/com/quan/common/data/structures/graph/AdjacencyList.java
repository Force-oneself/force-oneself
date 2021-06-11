package com.quan.common.data.structures.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description 邻接表
 * @Author Force-oneself
 * @Date 2021-06-10 20:56
 **/
public class AdjacencyList<T extends Comparable<T>> {

    private final List<Vertex> vertices;

    public AdjacencyList() {
        vertices = new ArrayList<>();
    }

    /**
     * 此方法从两个指定顶点之间的图中删除一条边
     *
     * @param from 边来自的顶点的数据
     * @param to   边缘将要到达的顶点的数据
     * @return 如果边不存在，则返回 false，如果边存在并被删除，则返回 true
     */
    public boolean removeEdge(T from, T to) {
        // 查看from节点是否存在
        Vertex fromVertex = this.getIfAbsent(from);
        return fromVertex != null && fromVertex.remove(to);
    }

    /**
     * 此方法在两个指定顶点之间向图中添加一条边
     *
     * @param from 边来自的顶点的数据
     * @param to   边缘将要到达的顶点的数据
     * @return 如果边不存在则返回真，如果边已经存在则返回假
     */
    public boolean addEdge(T from, T to) {
        // 查看节点是否存在
        Vertex fromVertex = this.getIfAbsent(from);
        Vertex toVertex = this.getIfAbsent(to);

        fromVertex = this.doAdd(from, fromVertex);
        toVertex = this.doAdd(to, toVertex);
        return fromVertex.add(toVertex);
    }

    /**
     * 添加元素
     *
     * @param data 顶点内容
     * @param dataVertex 存在或者创建的顶点
     * @return 顶点
     */
    private Vertex doAdd(T data, Vertex dataVertex) {
        if (dataVertex == null) {
            dataVertex = new Vertex(data);
            vertices.add(dataVertex);
        }
        return dataVertex;
    }

    private Vertex getIfAbsent(T from) {
        return vertices.stream().filter(v -> from.compareTo(v.getData()) == 0).findAny().orElse(null);
    }

    /**
     * @Description 顶点
     * @Author Force-oneself
     * @Date 2021-06-10 21:14
     **/
    private class Vertex {
        private final T data;
        private final List<Vertex> adjacentVertices;

        public Vertex(T data) {
            adjacentVertices = new ArrayList<>();
            this.data = data;
        }

        public boolean add(Vertex to) {
            Objects.requireNonNull(to, "顶点不能为null");
            if (!adjacentVertices.stream().allMatch(v -> v.getData().compareTo(to.getData()) != 0)) {
                return false;
            }
            adjacentVertices.add(to);
            return true;
        }

        public boolean remove(T to) {
            Objects.requireNonNull(to, "顶点数据不能为null");
            for (int i = 0; i < adjacentVertices.size(); i++) {
                if (adjacentVertices.get(i).data.compareTo(to) == 0) {
                    adjacentVertices.remove(i);
                    return true;
                }
            }
            return false;
        }

        public T getData() {
            return data;
        }

    }

}
