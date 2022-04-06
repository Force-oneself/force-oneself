package com.quan.algorithm.graph;

/**
 * @author Force-oneself
 * @description Edge
 * @date 2022-03-14
 */
public class Edge {

    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
