package com.quan.common.data.structures.graph.cus;

import java.util.Collection;

/**
 * @author Force-oneself
 * @Description Graph.java
 * @date 2021-06-10
 */
public interface Graph<V, E> {

    /**
     * 添加一条边
     *
     * @param edge 新增的边
     * @return 成功结果
     */
    boolean addEdge(E edge);

    /**
     * 删除一条边
     *
     * @param edge 需要删除的边
     * @return 成功结果
     */
    boolean removeEdge(E edge);

    /**
     * 添加顶点
     *
     * @param v 顶点
     * @return 成功结果
     */
    boolean addVertex(V v);

    /**
     * 删除顶点
     *
     * @param v 顶点
     * @return 成功结果
     */
    boolean removeVertex(V v);

    /**
     * 顶点数量
     *
     * @return 顶点数
     */
    int vertexSize();

    /**
     * 边的数量
     *
     * @return 边数量
     */
    int edgeSize();

    /**
     * 获取顶点集合
     *
     * @return 全部顶点
     */
    Collection<V> getVertexes();

    /**
     * 获取边集合
     *
     * @return 全部边
     */
    Collection<E> getEdges();
}
