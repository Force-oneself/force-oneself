package com.quan.leetcode.question.bs;

/**
 * @author Force-oneself
 * @description S_35
 * @date 2022-04-14
 */
public class S_35 {

    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int l = 0;
        int r = len - 1;
        while (l <= r) {
            if (l == r) return nums[l] >= target ? l : l + 1;
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(new S_35().searchInsert(new int[]{1, 3, 5, 6}, 5));
        System.out.println(new S_35().searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(new S_35().searchInsert(new int[]{1, 3, 5, 6}, 7));
        System.out.println(new S_35().searchInsert(new int[]{1, 3}, 3));
    }

}
