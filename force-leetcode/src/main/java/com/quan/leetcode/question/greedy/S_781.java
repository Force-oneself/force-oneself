package com.quan.leetcode.question.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Force-oneself
 * @description S_781
 * @date 2022-04-11
 */
public class S_781 {

    public int numRabbits(int[] answers) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int y : answers) {
            count.put(y, count.getOrDefault(y, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int y = entry.getKey(), x = entry.getValue();
            ans += (x + y) / (y + 1) * (y + 1);
        }
        return ans;
    }
}
