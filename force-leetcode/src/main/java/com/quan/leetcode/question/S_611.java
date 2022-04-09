package com.quan.leetcode.question;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description S_611
 * @date 2022-04-09
 */
public class S_611 {

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] > nums[k]) {
                        res++;
                    } else {
                        break;
                    }
                }
            }
        }
        return res;
    }

    public int triangleNumber1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                // 二分查
                int left = j + 1, right = n - 1, k = j;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (nums[mid] < nums[i] + nums[j]) {
                        k = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                ans += k - j;
            }
        }
        return ans;
    }

    // 双指针
    public int triangleNumber2(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            int x3 = nums[i];
            int j = i - 1, k = 0;
            while (k < j) {
                if (nums[k] + nums[j] > x3) {
                    // 当前k大于 就说明k-j区间的都满足
                    ans += j - k;
                    // j往前寻找更小的值在匹配
                    j--;
                } else {
                    // 当前值小于则往右寻找更大的值
                    k++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new S_611().triangleNumber(new int[]{2, 2, 3, 4}));
        System.out.println(new S_611().triangleNumber(new int[]{4, 2, 3, 4}));
    }
}
