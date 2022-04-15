package com.quan.leetcode.question.greedy;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description S_455
 * @date 2022-04-07
 */
public class S_455 {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int begin = 0;
        for (int k : s) {
            for (int j = begin; j < g.length; j++) {
                if (k >= g[j]) {
                    begin = j + 1;
                    res++;
                    break;
                }
            }
        }
        return res;
    }

}
