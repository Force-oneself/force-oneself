package com.quan.algorithm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Force-oneself
 * @description GraphNode
 * @date 2022-03-14
 */
public class GraphNode {

    public int value;
    public int in;
    public int out;
    public List<GraphNode> nextList;
    public List<Edge> edges;

    public GraphNode(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.edges = new ArrayList<>();
        this.nextList = new ArrayList<>();
    }
}
