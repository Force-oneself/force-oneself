package com.quan.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Force-oneself
 * @description Dijkstra
 * @date 2022-04-05
 */
public class Dijkstra {

    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        //key：从起始点出发到达key
        //value：从起始点出发到达key的最小距离
        //如果在表中，没有T的记录，含义是从from出发到T点的距离为正无穷
        distanceMap.put(from, 0);
        // 打过对号的点
        HashSet<Node> selectedNodes = new HashSet<>();

        //从未选择过的点(selectedNodes之外的)选择距离最小的点，selectedNodes是黑名单，不要从里面选
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            //  原始点  ->  minNode(跳转点)   最小距离distance
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else { // toNode
                    distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> touchedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!touchedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }


}
