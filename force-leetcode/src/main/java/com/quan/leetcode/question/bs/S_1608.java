package com.quan.leetcode.question.bs;

import java.util.Arrays;

/**
 * S_1608
 *
 * @author Force-oneself
 * @date 2022-04-26
 */
public class S_1608 {

    public int specialArray(int[] nums) {
        int len = nums.length;
        int l = 1;
        int r = len;
        while (l <= r) {
            int mid = (r + l) / 2;
            int match = iter(nums, mid);
            if (match == mid) {
                return mid;
            } else if (match > mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    private int iter(int[] nums, int mid) {
        int cnt = 0;
        for (int num : nums) {
            if (num >= mid) cnt++;
        }
        return cnt;
    }

    public int specialArray1(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int l = 1;
        int r = len;
        while (l <= r) {
            int mid = (r + l) / 2;
            int match = match(nums, mid);
            if (match == mid) {
                return mid;
            } else if (match > mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    private int match(int[] nums, int mid) {
        int l = 0;
        int len = nums.length;
        int r = len - 1;
        int ans = len;
        while (l <= r) {
            int m = (r + l) / 2;
            if (nums[m] >= mid) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return len - ans;
    }

    public static void main(String[] args) {
        System.out.println(new S_1608().specialArray(new int[]{3, 5}));
        System.out.println(new S_1608().specialArray(new int[]{0, 0}));
        System.out.println(new S_1608().specialArray(new int[]{0, 4, 3, 0, 4}));
        System.out.println(new S_1608().specialArray(new int[]{3, 6, 7, 7, 0}));
    }
}
