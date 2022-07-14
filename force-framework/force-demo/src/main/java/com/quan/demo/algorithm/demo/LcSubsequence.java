package com.quan.demo.algorithm.demo;

/**
 * LCSubsequence
 * 请注意区分子串和子序列的不同，给定两个字符串str1和str2，
 * 求两个字符的最长公共子序列
 *
 * 动态规划的空间压缩技巧
 *
 * @author Force-oneself
 * @date 2022-07-13
 */
public class LcSubsequence {

    public static int lcs(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        return process(str1, str2, n - 1, m - 1);
    }

    /***
     * str1[0....i1] 与 str2[0......i2]的最长公共子序列长度是多少？
     *
     * @param str1 str1
     * @param str2 str2
     * @param i1 i1
     * @param i2 i2
     * @return  /
     */
    public static int process(char[] str1, char[] str2, int i1, int i2) {
        if (i1 == 0 && i2 == 0) {
            return str1[i1] == str2[i2] ? 1 : 0;
        }
        // i1 和 i2 不同时为0
        if (i1 == 0) {
            // str1[0..0] str2[0...i2 - 1]
            return ((str1[i1] == str2[i2]) || process(str1, str2, i1, i2 - 1) == 1) ? 1 : 0;
        }

        if (i2 == 0) {
            return ((str1[i1] == str2[i2]) || process(str1, str2, i1 - 1, i2) == 1) ? 1 : 0;
        }
        // i1 和 i2 都不是0
        // 最长公共子序列结尾，不是以str1[i1]与str2[i2]结尾的
        int p1 = process(str1, str2, i1 - 1, i2 - 1);
        int p2 = process(str1, str2, i1, i2 - 1);
        int p3 = process(str1, str2, i1 - 1, i2);
        int p4 = 0;
        if (str1[i1] == str2[i2]) {
            p4 = p1 + 1;
        }
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static int dp(String s1, String s2) {

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int m = str2.length;

        int[][] dp = new int[n][m];

        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int j = 1; j < m; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public static String lcse(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int m = chs1.length - 1;
        int n = chs2.length - 1;
        char[] res = new char[dp[m][n]];
        int index = res.length - 1;
        while (index >= 0) {
            if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                n--;
            } else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                m--;
            } else {
                res[index--] = chs1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    public static int[][] getdp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String str1 = "A1BC23Z4";
        String str2 = "12O3YU4P";
//		System.out.println(lcse(str1, str2));

        System.out.println(lcs(str1, str2));
        System.out.println(dp(str1, str2));

    }
}
