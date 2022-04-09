package com.quan.leetcode.question;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Force-oneself
 * @description S_659
 * @date 2022-04-09
 */
public class S_659 {

    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> endMap = new HashMap<>();
        // 记录重复的值
        for (int x : nums) {
            int count = countMap.getOrDefault(x, 0) + 1;
            countMap.put(x, count);
        }
        for (int x : nums) {
            int count = countMap.getOrDefault(x, 0);
            if (count <= 0) {
                continue;
            }
            // x-1的值存在即和x组成序列
            int prevEndCount = endMap.getOrDefault(x - 1, 0);
            if (prevEndCount > 0) {
                // 组成序列则将重复值--
                countMap.put(x, count - 1);
                // x-1结尾的序列则需要--
                endMap.put(x - 1, prevEndCount - 1);
                // 此时就存在一个以x结尾的序列
                endMap.put(x, endMap.getOrDefault(x, 0) + 1);
                // 当没有与x组成序列的需要和x+1 x+2 判断是否可以组成
            } else {
                int count1 = countMap.getOrDefault(x + 1, 0);
                int count2 = countMap.getOrDefault(x + 2, 0);
                // 两个值都存在则可以组成一个新的序列
                if (count1 > 0 && count2 > 0) {
                    countMap.put(x, count - 1);
                    countMap.put(x + 1, count1 - 1);
                    countMap.put(x + 2, count2 - 1);
                    // 此时就存在一个以x结尾的序列
                    endMap.put(x + 2, endMap.getOrDefault(x + 2, 0) + 1);
                } else {
                    // 不能即返回结果无法拆出大于等于3的所有子序列
                    return false;
                }
            }
        }
        return true;
    }

}
