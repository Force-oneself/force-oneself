package com.quan.algorithm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Force-oneself
 * @description GraphNode
 * @date 2022-03-14
 */
public class Node {

    public int value;
    public int in;
    public int out;
    public List<Node> nextList;
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.edges = new ArrayList<>();
        this.nextList = new ArrayList<>();
    }
}
