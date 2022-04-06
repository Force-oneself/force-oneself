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

    public void bfs(Node node) {
        if (node == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();

        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // TODO 处理逻辑

            for (Node next : cur.nextList) {
                if (set.add(next)) {
                    queue.add(next);
                }
            }
        }
    }


    public void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        set.add(node);
        stack.push(node);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nextList) {
                if (set.add(next)) {
                    stack.add(cur);
                    stack.add(next);
                    break;
                }
            }
        }
    }
}
