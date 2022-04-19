package com.quan.leetcode.question.binarysearch;

/**
 * @author Force-oneself
 * @description S_704
 * @date 2022-04-19
 */
public class S_704 {


    public int search(int[] nums, int target) {
        int len = nums.length;
        int l = 0;
        int r = len - 1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}
