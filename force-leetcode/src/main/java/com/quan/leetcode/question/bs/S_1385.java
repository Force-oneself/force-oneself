package com.quan.leetcode.question.bs;

import java.util.Arrays;

/**
 * S_1385
 *
 * @author Force-oneself
 * @date 2022-04-24
 */
public class S_1385 {

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int len1 = arr1.length;
        int ans = len1;
        for (int j : arr1) {
            for (int k : arr2) {
                if (Math.abs(j - k) <= d) {
                    ans--;
                    break;
                }
            }
        }
        return ans;
    }

    public int findTheDistanceValue1(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int cnt = 0;
        for (int x : arr1) {
            int p = binarySearch(arr2, x);
            boolean ok = true;
            if (p < arr2.length) {
                ok &= arr2[p] - x > d;
            }
            if (p - 1 >= 0 && p - 1 <= arr2.length) {
                ok &= x - arr2[p - 1] > d;
            }
            cnt += ok ? 1 : 0;
        }
        return cnt;
    }

    public int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        if (arr[high] < target) {
            return high + 1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
