package com.quan.leetcode.question.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Force-oneself
 * @description S_646
 * @date 2022-04-09
 */
public class S_646 {

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        int pre = pairs[0][1];
        int res = 0;
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > pre) {
                res++;
                pre = pairs[i][1];
            }
        }
        return res;
    }

}
