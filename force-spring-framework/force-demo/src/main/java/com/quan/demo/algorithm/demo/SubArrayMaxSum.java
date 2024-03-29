package com.quan.demo.algorithm.demo;

/**
 * SubArrayMaxSum
 * <p>
 * 给定一个数组arr，返回子数组的最大累加和
 *
 * @author Force-oneself
 * @date 2022-07-13
 */
public class SubArrayMaxSum {

    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int j : arr) {
            cur += j;
            max = Math.max(max, cur);
            cur = Math.max(cur, 0);
        }
        return max;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {-2, -3, -5, 40, -10, -10, 100, 1};
        System.out.println(maxSum(arr1));

        int[] arr2 = {-2, -3, -5, 0, 1, 2, -1};
        System.out.println(maxSum(arr2));

        int[] arr3 = {-2, -3, -5, -1};
        System.out.println(maxSum(arr3));

    }
}
