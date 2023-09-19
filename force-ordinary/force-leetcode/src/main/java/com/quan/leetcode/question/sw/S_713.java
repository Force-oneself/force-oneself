package com.quan.leetcode.question.sw;

/**
 * S_713
 *
 * @author Force-oneself
 * @date 2022-05-01
 */
public class S_713 {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int multi = 1;
        int len = nums.length;
        int counter = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                multi *= nums[j];
                if (multi < k) {
                    counter++;
                } else {
                    break;
                }
            }
            multi = 1;
        }
        return counter;
    }

    /**
     * 二分
     *
     * @param nums nums
     * @param k    k
     * @return return
     */
    public int numSubarrayProductLessThanK1(int[] nums, int k) {
        if (k == 0) return 0;
        double logk = Math.log(k);
        double[] prefix = new double[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + Math.log(nums[i]);
        }

        int ans = 0;
        for (int i = 0; i < prefix.length; i++) {
            int lo = i + 1, hi = prefix.length;
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (prefix[mi] < prefix[i] + logk - 1e-9) lo = mi + 1;
                else hi = mi;
            }
            ans += lo - i - 1;
        }
        return ans;
    }

    /**
     * 双指针
     *
     * @param nums nums
     * @param k k
     * @return  return
     */
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) prod /= nums[left++];
            ans += right - left + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new S_713().numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
        System.out.println(new S_713().numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0));
    }
}
