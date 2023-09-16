package com.quan.demo.algorithm.sort;

/**
 * QuickSort 快排序
 *
 * @author Force-oneself
 * @date 2022-03-06
 */
public class QuickSort {


    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] index = partition(arr, l, r);
        quickSort(arr, l, index[0] - 1);
        quickSort(arr, index[1] + 1, r);
    }

    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                SortUtils.swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                SortUtils.swap(arr, --more, l);
            } else {
                l++;
            }
        }
        SortUtils.swap(arr, more, r);
        return new int[]{less + 1, more};
    }
}
