package com.quan.common.data.structures.graph;

/**
 * @author Force-oneself
 * @Description GraphDemo.java
 * @date 2021-06-11
 */
public class GraphDemo {


    public static void main(String[] args) {
        AdjacencyList<String> g = new AdjacencyList<>();
        g.addEdge("租户id1", "tenant2");
        g.addEdge("租户id2", "tenant2");
        g.addEdge("租户id3", "tenant2");
        g.addEdge("租户id4", "tenant2");
        g.addEdge("租户id1", "tenant23");
        g.addEdge("租户id1", "tenant5");
        g.addEdge("租户id1", "tenant6");
        g.addEdge("租户id1", "tenant7");
        g.addEdge("租户id1", "tenant8");


        System.out.println(g);
    }
}
