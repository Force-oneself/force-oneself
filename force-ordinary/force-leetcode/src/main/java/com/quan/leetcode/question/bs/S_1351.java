package com.quan.leetcode.question.bs;

/**
 * S_1351
 *
 * @author Force-oneself
 * @date 2022-04-24
 */
public class S_1351 {

    public int countNegatives(int[][] grid) {
        int sum = 0;
        for (int[] ints : grid) {
            sum += count(ints);
        }
        return sum;
    }

    public int count(int[] arr) {
        int l = 0;
        int len = arr.length;
        int r = len - 1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (arr[mid] >= 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return len - l;
    }

    public static void main(String[] args) {
        System.out.println(new S_1351().countNegatives(new int[][]{{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}}));
        System.out.println(new S_1351().countNegatives(new int[][]{{3, 2}, {1,0}}));
    }
}
