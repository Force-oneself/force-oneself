package com.quan.leetcode.question;

/**
 * @author Force-oneself
 * @description S_769
 * @date 2022-04-11
 */
public class S_769 {


    public int maxChunksToSorted(int[] arr) {
        int ans = 0, max = 0;
        for (int i = 0; i < arr.length; ++i) {
            max = Math.max(max, arr[i]);
            if (max == i) ans++;
        }
        return ans;
    }

}
