package com.quan.demo.algorithm;

/**
 * @author Force-oneself
 * @description SelectionSort
 * @date 2022-03-08
 */
public class SelectionSort {

    public static void sort(int[] arr) {
        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }
            // 将找到的最小值和i位置所在的值进行交换
            SortUtils.swap(arr, i, min);
        }
    }

}
