package com.quan.leetcode.question.dp;

/**
 * S_279
 *
 * @author Force-oneself
 * @date 2022-05-12
 */
public class S_279 {

    public int numSquares(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }
}
