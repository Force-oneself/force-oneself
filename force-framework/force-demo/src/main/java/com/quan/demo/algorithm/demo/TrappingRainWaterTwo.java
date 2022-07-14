package com.quan.demo.algorithm.demo;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * TrappingRainWaterII
 * 如果给你一个二维数组，每一个值表示这一块地形的高度，
 * 求整块地形能装下多少水。
 *
 * @author Force-oneself
 * @date 2022-07-13
 */
public class TrappingRainWaterTwo {

    public static class Node {
        public int value;
        public int row;
        public int col;

        public Node(int v, int r, int c) {
            value = v;
            row = r;
            col = c;
        }

    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }

    }

    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
            return 0;
        }
        int n = heightMap.length;
        int m = heightMap[0].length;
        // isEnter[i][j] == true  之前进过
        //  isEnter[i][j] == false 之前没进过
        boolean[][] isEnter = new boolean[n][m];
        // 小根堆
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
        // 把外围的一圈入堆
        for (int col = 0; col < m - 1; col++) {
            isEnter[0][col] = true;
            heap.add(new Node(heightMap[0][col], 0, col));
        }
        for (int row = 0; row < n - 1; row++) {
            isEnter[row][m - 1] = true;
            heap.add(new Node(heightMap[row][m - 1], row, m - 1));
        }
        for (int col = m - 1; col > 0; col--) {
            isEnter[n - 1][col] = true;
            heap.add(new Node(heightMap[n - 1][col], n - 1, col));
        }
        for (int row = n - 1; row > 0; row--) {
            isEnter[row][0] = true;
            heap.add(new Node(heightMap[row][0], row, 0));
        }

        // 每个位置的水量，累加到water上去
        int water = 0;
        // 每个node在弹出的时候，如果value更大，更新max，否则max的值维持不变
        int max = 0;
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            max = Math.max(max, cur.value);
            int r = cur.row;
            int c = cur.col;
            // 如果有上面的位置并且上面位置没进过堆
            if (r > 0 && !isEnter[r - 1][c]) {
                water += Math.max(0, max - heightMap[r - 1][c]);
                isEnter[r - 1][c] = true;
                heap.add(new Node(heightMap[r - 1][c], r - 1, c));
            }
            // 下面位置
            if (r < n - 1 && !isEnter[r + 1][c]) {
                water += Math.max(0, max - heightMap[r + 1][c]);
                isEnter[r + 1][c] = true;
                heap.add(new Node(heightMap[r + 1][c], r + 1, c));
            }
            // 左边位置
            if (c > 0 && !isEnter[r][c - 1]) {
                water += Math.max(0, max - heightMap[r][c - 1]);
                isEnter[r][c - 1] = true;
                heap.add(new Node(heightMap[r][c - 1], r, c - 1));
            }
            // 右边位置
            if (c < m - 1 && !isEnter[r][c + 1]) {
                water += Math.max(0, max - heightMap[r][c + 1]);
                isEnter[r][c + 1] = true;
                heap.add(new Node(heightMap[r][c + 1], r, c + 1));
            }
        }
        return water;
    }
}
