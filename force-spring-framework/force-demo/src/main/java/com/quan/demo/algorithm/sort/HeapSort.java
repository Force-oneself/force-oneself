package com.quan.demo.algorithm.sort;

/**
 * HeapSort 堆排序
 *
 * @author Force-oneself
 * @date 2022-03-07
 */
public class HeapSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int heapSize = arr.length;
        for (int i = 0; i < heapSize; i++) {
            heapInsert(arr, i);
        }
        SortUtils.swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            SortUtils.swap(arr, 0, --heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                    ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            SortUtils.swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void heapInsert(int[] arr, int i) {
        // 与父节点比较，大于进行交换
        while (arr[i] > arr[(i - 1) / 2]) {
            SortUtils.swap(arr, i, (i - 1) / 2);
            // 下标换成父节点位置
            i = (i - 1) / 2;
        }
    }
}
