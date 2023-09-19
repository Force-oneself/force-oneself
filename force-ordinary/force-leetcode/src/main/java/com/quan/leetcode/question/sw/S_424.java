package com.quan.leetcode.question.sw;

/**
 * S_424
 *
 * @author Force-oneself
 * @date 2022-04-30
 */
public class S_424 {

    public int characterReplacement(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        int left = 0, right = 0;
        while (right < n) {
            num[s.charAt(right) - 'A']++;
            // 最大重复的元素
            maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
            // 说明这个区间内的不重复的元素大于k则left 右移
            if (right - left + 1 - maxn > k) {
                // 将最左边的元素--
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }

    public static void main(String[] args) {
        System.out.println(new S_424().characterReplacement("ABBB", 2));
        System.out.println(new S_424().characterReplacement("AABABBA", 1));
    }

}
