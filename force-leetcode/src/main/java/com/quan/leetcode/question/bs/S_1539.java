package com.quan.leetcode.question.bs;

/**
 * S_1539
 *
 * @author Force-oneself
 * @date 2022-04-26
 */
public class S_1539 {

    /**
     * 穷举
     *
     * @param arr arr
     * @param k   k
     * @return return
     */
    public int findKthPositive1(int[] arr, int k) {
        int missCount = 0, lastMiss = -1, current = 1, ptr = 0;
        for (missCount = 0; missCount < k; ++current) {
            if (current == arr[ptr]) {
                ptr = (ptr + 1 < arr.length) ? ptr + 1 : ptr;
            } else {
                ++missCount;
                lastMiss = current;
            }
        }
        return lastMiss;
    }

    /**
     * 二分
     *
     * @param arr arr
     * @param k   k
     * @return return
     */
    public int findKthPositive2(int[] arr, int k) {
        if (arr[0] > k) {
            return k;
        }

        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            int x = mid < arr.length ? arr[mid] : Integer.MAX_VALUE;
            if (x - mid - 1 >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return k - (arr[l - 1] - (l - 1) - 1) + arr[l - 1];
    }

    public int findKthPositive(int[] arr, int k) {
        for (int j : arr) {
            if (j <= k) {
                k++;
            }
        }
        return k;
    }
}
