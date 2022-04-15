package com.quan.leetcode.question.binarysearch;

/**
 * @author Force-oneself
 * @description S_162
 * @date 2022-04-15
 */
public class S_162 {

    public int findPeakElement(int[] nums) {
        int len = nums.length;
        int l = 0;
        int r = len - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
