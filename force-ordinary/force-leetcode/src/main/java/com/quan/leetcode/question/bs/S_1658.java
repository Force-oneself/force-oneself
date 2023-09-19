package com.quan.leetcode.question.bs;

/**
 * S_1658
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public class S_1658 {

    public int minOperations(int[] nums, int x) {
        return op(nums, 0, nums.length - 1, x);
    }

    public int op(int[] nums, int l, int r, int x) {
        if (l > r || x < 0) {
            return -1;
        }
        if (x == 0) {
            return 0;
        }
        int left = op(nums, l + 1, r, x - nums[l]);
        int right = op(nums, l, r - 1, x - nums[r]);
        if (left < 0 && right < 0) {
            return -1;
        }
        if (left >= 0 && right >= 0) {
            return Math.min(left, right) + 1;
        }
        return left >= 0 ? left + 1 : right + 1;
    }

    /**
     * 滑动窗口
     *
     * @param nums nums
     * @param x    x
     * @return return
     */
    public int minOperations1(int[] nums, int x) {
        int n = nums.length;
        int left = 0, right = 0;
        int maxLength = -1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        int count = sum - x;
        if (count < 0) {
            return -1;
        }
        int sumNums = 0;
        while (right < n) {
            sumNums += nums[right];
            while (sumNums > count) {
                sumNums -= nums[left];
                left++;
            }
            if (sumNums == count) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
            right++;
        }
        if (maxLength == -1) return -1;
        else return n - maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new S_1658().minOperations(new int[]{1, 1, 4, 2, 3}, 5));
        System.out.println(new S_1658().minOperations(new int[]{5, 6, 7, 8, 9}, 4));
        System.out.println(new S_1658().minOperations(new int[]{3, 2, 20, 1, 1, 3}, 10));
    }
}
