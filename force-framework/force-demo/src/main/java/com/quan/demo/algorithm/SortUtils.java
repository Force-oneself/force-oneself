package com.quan.demo.algorithm;

/**
 * @author Force-oneself
 * @description SortUtils
 * @date 2022-03-06
 */
public final class SortUtils {

    /**
     * 数组交换
     *
     * @param arr arr
     * @param l   l
     * @param r   r
     */
    public static void swap(int[] arr, int l, int r) {
        if (l != r) {
            int t = arr[l];
            arr[l] = arr[r];
            arr[r] = t;
        }
    }

    public static int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

}
