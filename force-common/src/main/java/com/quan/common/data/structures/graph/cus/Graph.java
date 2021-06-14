package com.quan.common.data.structures.graph.cus;

/**
 * @author Force-oneself
 * @Description Graph.java
 * @date 2021-06-10
 */
public interface Graph<V, E> {

    /**
     * 添加一条边
     *
     * @param from 出度顶点
     * @param to 入度顶点
     * @return 成功结果
     */
    boolean addEdge(V from, V to);

    /**
     * 删除一条边
     *
     * @param from 出度顶点
     * @param to 入度顶点
     * @return 成功结果
     */
    boolean removeEdge(V from, V to);

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

}
