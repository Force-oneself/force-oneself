package com.quan.algorithm.graph;

import java.util.*;

/**
 * @author Force-oneself
 * @description Demo
 * @date 2022-03-14
 */
public class Demo {


    public static void main(String[] args) {
    }

    public void bfs(GraphNode node) {
        if (node == null) {
            return;
        }

        Queue<GraphNode> queue = new LinkedList<>();
        Set<GraphNode> set = new HashSet<>();

        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            // TODO 处理逻辑

            for (GraphNode next : cur.nextList) {
                if (set.add(next)) {
                    queue.add(next);
                }
            }
        }
    }


    public void dfs(GraphNode node) {
        if (node == null) {
            return;
        }
        Stack<GraphNode> stack = new Stack<>();
        Set<GraphNode> set = new HashSet<>();
        set.add(node);
        stack.push(node);
        while (!stack.isEmpty()) {
            GraphNode cur = stack.pop();
            for (GraphNode next : cur.nextList) {
                if (set.add(next)) {
                    stack.add(cur);
                    stack.add(next);
                    break;
                }
            }
        }
    }
}
