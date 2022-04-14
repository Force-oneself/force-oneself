package com.quan.leetcode.question.binarysearch;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description S_34
 * @date 2022-04-14
 */
public class S_34 {

    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int l = 0;
        int r = len - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) return search(nums, mid, target);
            else if (nums[mid] > target) r = mid - 1;
            else l = mid + 1;
        }
        return new int[]{-1, -1};
    }

    public int[] search(int[] nums, int i, int target) {
        int l = i;
        int r = i;
        while (l > 0 && nums[l - 1] == target) l--;
        while (r < nums.length - 1 && nums[r + 1] == target) r++;
        return new int[]{l, r};
    }

    public static void main(String[] args) {
        Arrays.stream(new S_34().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)).forEach(System.out::print);
        System.out.println();
        Arrays.stream(new S_34().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)).forEach(System.out::print);
        System.out.println();
        Arrays.stream(new S_34().searchRange(new int[]{}, 0)).forEach(System.out::print);
        System.out.println();
    }
}
