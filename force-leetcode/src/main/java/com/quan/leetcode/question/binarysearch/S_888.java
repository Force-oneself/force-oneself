package com.quan.leetcode.question.binarysearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * S_888
 *
 * @author Force-oneself
 * @date 2022-04-21
 */
public class S_888 {


    /**
     * x=y+(sumA−sumB)/2
     *
     *
     * @param aliceSizes aliceSizes
     * @param bobSizes bobSizes
     * @return  return
     */
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sumA = Arrays.stream(aliceSizes).sum();
        int sumB = Arrays.stream(bobSizes).sum();
        // 差值
        int delta = (sumA - sumB) / 2;
        Set<Integer> rec = new HashSet<>();
        // alice的数据填hash表内
        for (int num : aliceSizes) {
            rec.add(num);
        }
        int[] ans = new int[2];
        for (int y : bobSizes) {
            int x = y + delta;
            if (rec.contains(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }
}
