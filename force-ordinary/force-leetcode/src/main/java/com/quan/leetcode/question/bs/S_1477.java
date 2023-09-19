package com.quan.leetcode.question.bs;

import java.util.ArrayList;
import java.util.Collections;

/**
 * S_1477
 *
 * @author Force-oneself
 * @date 2022-04-25
 */
public class S_1477 {

    public int minSumOfLengths(int[] arr, int target) {
        int right = 0;
        int left = 0;
        int sum = 0;
        ArrayList<int[]> list = new ArrayList<>();
        // 求出所有符合要求的子数组
        int len = arr.length;
        while (right < len) {
            sum += arr[right];
            right++;
            if (sum < target) {
                continue;
            }
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            if (sum == target) {
                list.add(new int[]{right - left, left});
            }
        }
        //结果按长度升序排序
        Collections.sort(list, (o1, o2) -> o1[0] - o2[0]);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            //ans是两个长度之和，如果遍历到有超过这个长度的，后面的不是最小值无需遍历。
            int[] a = list.get(i);
            if (a[0] * 2 >= ans) {
                break;
            }
            for (int j = i + 1; j < list.size(); j++) {
                int arr1[] = list.get(i);
                int arr2[] = list.get(j);
                if (arr1[1] < arr2[1] && arr1[0] + arr1[1] > arr2[1]) {
                    continue;
                }
                if (arr2[1] < arr1[1] && arr2[0] + arr2[1] > arr1[1]) {
                    continue;
                }
                ans = Math.min(ans, arr1[0] + arr2[0]);
                //长度经过排序之后，后面的长度比当前大不满足最小，所以舍去不要进行遍历
                break;
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
