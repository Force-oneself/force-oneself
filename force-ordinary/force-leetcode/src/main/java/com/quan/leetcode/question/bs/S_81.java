package com.quan.leetcode.question.bs;

/**
 * @author Force-oneself
 * @description S_81
 * @date 2022-04-15
 */
public class S_81 {

    public boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return nums[0] == target;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return true;
            }
            // 左右两边相等 即需要和中间值一起抛弃
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                ++l;
                --r;
            } else if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new S_81().search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        System.out.println(new S_81().search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
        System.out.println(new S_81().search(new int[]{2, 5, 6, 0, 0, 1, 2}, 1));
        System.out.println(new S_81().search(new int[]{1, 0, 1, 1, 1, 1, 1}, 0));
    }
}
