package com.quan.leetcode.question.sw;

/**
 * S_930
 *
 * @author Force-oneself
 * @date 2022-05-01
 */
public class S_930 {

    public int numSubarraysWithSum(int[] nums, int goal) {
        int len = nums.length;
        int r = 0;
        int l = 0;
        int count = 0;
        int sum = 0;
        while (r < len) {
            sum += nums[r];
            while (l < r && sum == goal) {
                sum -= nums[l];
                l++;
                count++;
            }
            r++;
        }
        return count;
    }

    public int numSubarraysWithSum1(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int ret = 0;
        while (right < n) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            ret += left2 - left1;
            right++;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new S_930().numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
        System.out.println(new S_930().numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0));
    }

}
