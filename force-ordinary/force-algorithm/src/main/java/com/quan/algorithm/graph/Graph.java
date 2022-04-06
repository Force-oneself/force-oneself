package com.quan.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Force-oneself
 * @description Graph
 * @date 2022-03-14
 */
public class Graph {

    public Map<Integer, Node> nodes;

    public Set<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
