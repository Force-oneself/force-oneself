package com.quan.leetcode.question.bs;

/**
 * @author Force-oneself
 * @description S_240
 * @date 2022-04-15
 */
public class S_240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            if (!(row[0] <= target && target <= row[row.length - 1])) continue;
            int index = search(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Z字查询
     *
     * @param matrix matrix
     * @param target target
     * @return return
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                --y;
            } else {
                ++x;
            }
        }
        return false;
    }
}
