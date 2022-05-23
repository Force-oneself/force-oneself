package com.quan.demo.algorithm;

/**
 * Walk
 *
 * @author Force-oneself
 * @date 2022-05-21
 */
public class Walk {


    public static int walk(int n, int m, int k, int p) {
        if (n < 2 || k < 1 || m < 1 || m > n || p < 1 || p < n) {
            return 0;
        }
        return doWalk(n, m, k, p);

    }

    /**
     * 暴力
     *
     * @param n    格子长度
     * @param cur  当前位置
     * @param rest 剩余步数
     * @param p    目标位置
     * @return 可行数
     */
    public static int doWalk(int n, int cur, int rest, int p) {
        // 没有步数可走了，看当前位置知否在目标位置，是就返回1
        if (rest == 0) {
            return cur == p ? 1 : 0;
        }
        // 在最左边只能右移
        if (cur == 1) {
            return doWalk(n, 2, rest - 1, p);
        }
        // 在最右边只能左移
        if (cur == n) {
            return doWalk(n, n - 1, rest - 1, p);
        }
        // 左右都可以走
        return doWalk(n, cur - 1, rest - 1, n) + doWalk(n, cur + 1, rest - 1, p);
    }

    public static int walk1(int n, int m, int k, int p) {
        if (n < 2 || k < 1 || m < 1 || m > n || p < 1 || p < n) {
            return 0;
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        return doWalk(n, m, k, p, dp);

    }

    /**
     * 记忆化搜索
     *
     * @param n    格子长度
     * @param cur  当前位置
     * @param rest 剩余步数
     * @param p    目标位置
     * @return 可行数
     */
    public static int doWalk(int n, int cur, int rest, int p, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ant;
        if (rest == 0) {
            // 没有步数可走了，看当前位置知否在目标位置，是就返回1
            ant = cur == p ? 1 : 0;
        } else if (cur == 1) {
            // 在最左边只能右移
            ant = doWalk(n, 2, rest - 1, p, dp);
        } else if (cur == n) {
            // 在最右边只能左移
            ant = doWalk(n, n - 1, rest - 1, p, dp);
        } else {
            // 左右都可以走
            ant = doWalk(n, cur - 1, rest - 1, n, dp) + doWalk(n, cur + 1, rest - 1, p, dp);
        }
        dp[cur][rest] = ant;
        return ant;
    }
}
