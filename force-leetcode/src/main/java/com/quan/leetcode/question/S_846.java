package com.quan.leetcode.question;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Force-oneself
 * @description S_846
 * @date 2022-04-12
 */
public class S_846 {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : hand) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        for (int x : hand) {
            if (!cnt.containsKey(x)) {
                continue;
            }
            for (int j = 0; j < groupSize; j++) {
                int num = x + j;
                // 该数不存在，即返回
                if (!cnt.containsKey(num)) {
                    return false;
                }
                // 将组成的列表-1
                cnt.put(num, cnt.get(num) - 1);
                if (cnt.get(num) == 0) {
                    cnt.remove(num);
                }
            }
        }
        return true;
    }
}
