package com.quan.leetcode.question.dp;

/**
 * S_375
 *
 * @author Force-oneself
 * @date 2022-05-15
 */
public class S_375 {


    public int getMoneyAmount(int n) {
        int[][] f = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                f[i][j] = j + f[i][j - 1];
                for (int k = i; k < j; k++) {
                    f[i][j] = Math.min(f[i][j], k + Math.max(
                            // 猜大了
                            f[i][k - 1],
                            // 猜小了
                            f[k + 1][j]));
                }
            }
        }
        return f[1][n];
    }
}
