package com.quan.demo.algorithm.demo;

/**
 * SubMatrixMaxSum
 * 给定一个整型矩阵，返回子矩阵的最大累计和
 *
 * @author Force-oneself
 * @date 2022-07-13
 */
public class SubMatrixMaxSum {

    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] s = null;
        // 开始的行号i
        for (int i = 0; i != m.length; i++) {
            // 从i～j的累加压缩数组
            s = new int[m[0].length];
            // 结束的行号j，i~j行是我讨论的范围
            for (int j = i; j != m.length; j++) {
                cur = 0;
                // 相当于求一维的最大子数组累加和
                for (int k = 0; k != s.length; k++) {
                    s[k] += m[j][k];
                    cur += s[k];
                    max = Math.max(max, cur);
                    cur = Math.max(cur, 0);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {{-90, 48, 78}, {64, -40, 64}, {-81, -7, 66}};
        System.out.println(maxSum(matrix));

    }
}
