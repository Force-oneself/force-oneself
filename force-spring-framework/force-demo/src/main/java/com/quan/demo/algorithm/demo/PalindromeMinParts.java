package com.quan.demo.algorithm.demo;

/**
 * PalindromeMinParts
 *
 * @author Force-oneself
 * @date 2022-07-21
 */
public class PalindromeMinParts {

    public static int minParts(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        boolean[][] isP = new boolean[n][n];
        // 斜线上的一个字符 即为true
        for (int i = 0; i < n; i++) {
            isP[i][i] = true;
        }
        // 与斜线上的字符组成两个字符 比较两者相等即为 回文字符串
        for (int i = 0; i < n - 1; i++) {
            isP[i][i + 1] = str[i] == str[i + 1];
        }
        for (int row = n - 3; row >= 0; row--) {
            for (int col = row + 2; col < n; col++) {
                isP[row][col] = str[row] == str[col] && isP[row + 1][col - 1];
            }
        }
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int end = i; end < n; end++) {
                // i..end
                if (isP[i][end]) {
                    dp[i] = Math.min(dp[i], 1 + dp[end + 1]);
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String test = "aba12321412321TabaKFK";
        System.out.println(minParts(test));
    }
}
