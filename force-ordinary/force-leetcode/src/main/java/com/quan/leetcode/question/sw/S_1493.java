package com.quan.leetcode.question.sw;

/**
 * S_1493
 *
 * @author Force-oneself
 * @date 2022-05-03
 */
public class S_1493 {

    public int longestSubarray(int[] nums) {
        int n = nums.length, res = 0, cnt = 0;
        for (int i = 0, j = 0; i < n; i++) {
            if (nums[i] == 0) cnt++;
            while (cnt > 1) {
                if (nums[j++] == 0) cnt--;
            }
            res = Math.max(res, i - j);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new S_1493().longestSubarray(new int[]{1,1,0,1}));
        System.out.println(new S_1493().longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}));
        System.out.println(new S_1493().longestSubarray(new int[]{1,1,1}));
    }
}
