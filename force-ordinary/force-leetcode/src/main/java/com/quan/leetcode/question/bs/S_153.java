package com.quan.leetcode.question.bs;

/**
 * @author Force-oneself
 * @description S_153
 * @date 2022-04-15
 */
public class S_153 {

    public int findMin(int[] nums) {
        int len = nums.length;
        int l = 0;
        int r = len - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            // 中间大于左边说明左边有序
            if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        System.out.println(new S_153().findMin(new int[]{5, 6, 7, 1, 2, 3}));
    }
}
