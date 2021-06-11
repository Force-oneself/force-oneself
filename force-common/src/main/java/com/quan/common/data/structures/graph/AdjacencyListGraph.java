package com.quan.common.data.structures.graph;

import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Objects;

/**
 * @Description 邻接表
 * @Author Force-oneself
 * @Date 2021-06-10 20:56
 **/
public class AdjacencyListGraph<E extends Comparable<E>> {

    ArrayList<Vertex> vertices;

    public AdjacencyListGraph() {
        vertices = new ArrayList<>();
    }

    /**
     * 此方法从两个指定顶点之间的图中删除一条边
     *
     * @param from 边来自的顶点的数据
     * @param to   边缘将要到达的顶点的数据
     * @return 如果边不存在，则返回 false，如果边存在并被删除，则返回 true
     */
    public boolean removeEdge(E from, E to) {
        // 查看from节点是否存在
        Vertex fromV = vertices.stream().filter(v -> from.compareTo(v.data) == 0).findAny().orElse(null);
        return fromV != null && fromV.remove(to);
    }

    /**
     * 此方法在两个指定顶点之间向图中添加一条边
     *
     * @param from 边来自的顶点的数据
     * @param to   边缘将要到达的顶点的数据
     * @return 如果边不存在则返回真，如果边已经存在则返回假
     */
    public boolean addEdge(E from, E to) {
        // 查看节点是否存在
        Vertex fromV = vertices.stream().filter(v -> from.compareTo(v.data) == 0).findAny().orElse(null);
        Vertex toV = vertices.stream().filter(v -> to.compareTo(v.data) == 0).findAny().orElse(null);

        if (fromV == null) {
            fromV = new Vertex(from);
            vertices.add(fromV);
        }
        if (toV == null) {
            toV = new Vertex(to);
            vertices.add(toV);
        }
        return fromV.add(toV);
    }

    /**
     * 这给出了图中的顶点列表及其邻接
     *
     * @return 返回描述此图的字符串
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices) {
            sb.append("Vertex: ");
            sb.append(v.data);
            sb.append("\n");
            sb.append("Adjacent vertices: ");
            for (Vertex v2 : v.adjacentVertices) {
                sb.append(v2.data);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * @Description 顶点
     * @Author Force-oneself
     * @Date 2021-06-10 21:14
     **/
    private class Vertex {
        E data;
        ArrayList<Vertex> adjacentVertices;

        public Vertex(E data) {
            adjacentVertices = new ArrayList<>();
            this.data = data;
        }

        public boolean add(Vertex to) {
            Objects.requireNonNull(to, "vertex is not null");
            return adjacentVertices.stream().allMatch(v -> v.data.compareTo(to.data) != 0)
                    && adjacentVertices.add(to); // this will return true;
        }

        public boolean remove(E to) {
            Objects.requireNonNull(to, "data is not null");
            for (int i = 0; i < adjacentVertices.size(); i++) {
                if (adjacentVertices.get(i).data.compareTo(to) == 0) {
                    adjacentVertices.remove(i);
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        assert !graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
        assert !graph.addEdge(2, 3);
        System.out.println(graph);
    }

}
