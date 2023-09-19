package com.quan.leetcode.question.bs;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description S_475
 * @date 2022-04-18
 */
public class S_475 {


    /**
     * 排序+二分
     *
     * @param houses  houses
     * @param heaters heaters
     * @return return
     */
    public int findRadius1(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        for (int i = 0, j = 0; i < houses.length; i++) {
            int curDistance = Math.abs(houses[i] - heaters[j]);
            while (j < heaters.length - 1
                    && Math.abs(houses[i] - heaters[j]) >= Math.abs(houses[i] - heaters[j + 1])) {
                j++;
                curDistance = Math.min(curDistance, Math.abs(houses[i] - heaters[j]));
            }
            ans = Math.max(ans, curDistance);
        }
        return ans;
    }


    /**
     * 二分
     *
     * @param houses  houses
     * @param heaters heaters
     * @return return
     */
    public int findRadius(int[] houses, int[] heaters) {
        int ans = 0;
        Arrays.sort(heaters);
        for (int house : houses) {
            // 左边的取暖器
            int i = binarySearch(heaters, house);
            // 右边的取暖器
            int j = i + 1;
            // 左边间隔
            int leftDistance = i < 0 ? Integer.MAX_VALUE : house - heaters[i];
            // 右边的间隔
            int rightDistance = j >= heaters.length ? Integer.MAX_VALUE : heaters[j] - house;
            // 取小的间隔即可满足
            int curDistance = Math.min(leftDistance, rightDistance);
            // 全局需要取最大的才可以满足全局
            ans = Math.max(ans, curDistance);
        }
        return ans;
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // houses[0] > target 说明取右边的取暖器
        if (nums[left] > target) {
            return -1;
        }
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
