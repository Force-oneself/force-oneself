package com.quan.leetcode.question.binarysearch;

/**
 * @author Force-oneself
 * @description BinarySearch
 * @date 2022-04-14
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        int len = nums.length;
        int l = 0;
        int r = len - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) r = mid - 1;
            else l = mid + 1;
        }
        return -1;
    }
}
