package com.quan.leetcode.question;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description S_870
 * @date 2022-04-12
 */
public class S_870 {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        int n = nums2.length;
        int[] ans = new int[n];
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i] = new int[]{nums2[i], i};
        }
        Arrays.sort(nums, (a, b) -> a[0] - b[0]);
        int start = 0;
        int end = n - 1;
        for (int i = 0; i < n; i++) {
            // 最小值小于对面最小值，和对面最强的一换一,先不减支
            if (nums1[i] <= nums[start][0]) {
                int index = nums[end][1];
                ans[index] = nums1[i];
                end--;
            } else {
                int index = nums[start][1];
                ans[index] = nums1[i];
                start++;
            }
        }
        return ans;
    }


}
