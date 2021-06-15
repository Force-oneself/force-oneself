package com.quan.common.data.structures.graph.cus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Force-oneself
 * @Description AbstractAdjacencyListGraph
 * @date 2021-06-14
 */
public abstract class AbstractAdjacencyListGraph<V extends Vertex<?>, E extends Edge<V>>
        implements AdjacencyListGraph<V, E>{

    protected Collection<V> vertexes;

    protected Collection<E> edges;

    protected AbstractAdjacencyListGraph() {
        this.vertexes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    protected AbstractAdjacencyListGraph(Collection<V> vertexes, Collection<E> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    @Override
    public boolean addEdge(E edge) {
        Objects.requireNonNull(edge, "edge is must not null");
        return this.edges.add(edge);
    }

    @Override
    public boolean removeEdge(E edge) {
        return this.edges.remove(edge);
    }

    @Override
    public boolean addVertex(V v) {
        Objects.requireNonNull(v, "vertex is must not null");
        return this.vertexes.add(v);
    }

    @Override
    public boolean removeVertex(V v) {
        return this.vertexes.remove(v);
    }

    @Override
    public int vertexSize() {
        return this.vertexes.size();
    }

    @Override
    public int edgeSize() {
        return this.edges.size();
    }

    @Override
    public Collection<V> getVertexes() {
        return this.vertexes;
    }

    @Override
    public Collection<E> getEdges() {
        return this.edges;
    }
}

