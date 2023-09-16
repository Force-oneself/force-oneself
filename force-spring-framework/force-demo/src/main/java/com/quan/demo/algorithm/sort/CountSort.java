package com.quan.demo.algorithm.sort;

import java.util.Arrays;

/**
 * CountSort 计数排序
 *
 * @author Force-oneself
 * @date 2022-03-07
 */
public class CountSort {

    public static int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int maxValue = SortUtils.getMaxValue(arr);
        return countingSort(arr, maxValue);
    }

    private static int[] countingSort(int[] arr, int maxValue) {
        // 适合密集性数组，费内存
        int bucketLen = maxValue + 1;
        int[] bucket = new int[bucketLen];
        // 构建桶
        for (int value : arr) {
            bucket[value]++;
        }

        int sortedIndex = 0;
        for (int j = 0; j < bucketLen; j++) {
            while (bucket[j] > 0) {
                arr[sortedIndex++] = j;
                bucket[j]--;
            }
        }
        return arr;
    }


}
