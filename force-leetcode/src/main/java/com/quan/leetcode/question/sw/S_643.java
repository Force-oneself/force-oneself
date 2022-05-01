package com.quan.leetcode.question.sw;

/**
 * S_643
 *
 * @author Force-oneself
 * @date 2022-05-01
 */
public class S_643 {

    public double findMaxAverage1(int[] nums, int k) {
        double sum = 0;
        for (int i = 0; i < k - 1; i++) {
            sum += nums[i];
        }
        int len = nums.length;
        double avg = -10000;
        for (int i = k - 1; i < len; i++) {
            sum += nums[i];
            avg = Math.max(avg, sum / k);
            sum -= nums[i - k + 1];
        }
        return avg;
    }

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return 1.0 * maxSum / k;
    }

    public static void main(String[] args) {
//        System.out.println(new S_643().findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
//        System.out.println(new S_643().findMaxAverage(new int[]{5}, 1));
        System.out.println(new S_643().findMaxAverage(new int[]{-1}, 1));
    }
}
