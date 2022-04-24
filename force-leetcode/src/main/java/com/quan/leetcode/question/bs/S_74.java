package com.quan.leetcode.question.bs;

/**
 * @author Force-oneself
 * @description S_74
 * @date 2022-04-14
 */
public class S_74 {

    /**
     * 时间复杂度O(N + lgN)
     *
     * @param matrix matrix
     * @param target target
     * @return  return
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        int len = matrix[0].length;
        for (int[] m : matrix) {
            if (!(m[0] <= target && m[len - 1] >= target)) continue;
            int l = 0;
            int r = len - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (m[mid] == target) return true;
                else if (m[mid] > target) r = mid - 1;
                else l = mid + 1;
            }
        }
        return false;
    }

    /**
     * 优化二维数组查询 时间复杂度O(lgN + lgN)
     *
     * @param matrix matrix
     * @param target target
     * @return  return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowLen = matrix[0].length;
        int len = matrix.length;
        int l = 0;
        int r = len - 1;
        // 二分查
        while (l <= r) {
            int mid = (l + r) / 2;
            // 查到该行数组
            if (matrix[mid][0] <= target && matrix[mid][rowLen - 1] >= target) {
                int rowl = 0;
                int rowr = rowLen - 1;
                int[] row = matrix[mid];
                // 二分查
                while (rowl <= rowr) {
                    int rowMid = (rowl + rowr) / 2;
                    if (row[rowMid] == target) return true;
                    else if (row[rowMid] > target) rowr = rowMid - 1;
                    else rowl = rowMid + 1;
                }
                return false;
            } else if (matrix[mid][0] > target) r = mid - 1;
            else l = mid + 1;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new S_74().searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
        System.out.println(new S_74().searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
        System.out.println(new S_74().searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 23));
    }

}
