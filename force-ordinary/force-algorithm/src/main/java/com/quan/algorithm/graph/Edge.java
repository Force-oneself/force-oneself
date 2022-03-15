package com.quan.algorithm.graph;

/**
 * @author Force-oneself
 * @description Edge
 * @date 2022-03-14
 */
public class Edge {

    public int weight;
    public GraphNode from;
    public GraphNode to;

    public Edge(int weight, GraphNode from, GraphNode to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
