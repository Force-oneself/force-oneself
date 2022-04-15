package com.quan.leetcode.question.greedy;

/**
 * @author Force-oneself
 * @description S_807
 * @date 2022-04-11
 */
public class S_807 {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int len = grid.length;

        int[] x = new int[len];
        int[] y = new int[len];
        // 每一行的最大值
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                x[i] = Math.max(x[i], grid[i][j]);
                y[j] = Math.max(y[j], grid[i][j]);
            }
        }

        int sum = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                // 行列相交的地方需要行列都满足小于最大值，所以需最小值减去原数组值即为可增加的值
                sum += Math.min(x[i], y[j]) - grid[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new S_807().maxIncreaseKeepingSkyline(new int[][]{{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}}));
        System.out.println(new S_807().maxIncreaseKeepingSkyline(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

}
