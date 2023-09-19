package com.quan.leetcode.question.bs;

/**
 * @author Force-oneself
 * @description S_33
 * @date 2022-04-14
 */
public class S_33 {

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            // 中间值
            if (nums[mid] == target) {
                return mid;
            }
            // 中间大于左边,说明左边是有序的
            if (nums[0] <= nums[mid]) {
                // target 在区间 [0, mid)
                if (nums[0] <= target && target < nums[mid]) {
                    // 区间往左边收缩
                    r = mid - 1;
                } else {
                    // 往右边区间靠
                    l = mid + 1;
                }
            } else {
                // target 在区间 (mid, len)
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
