package com.quan.leetcode.question.bs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Force-oneself
 * @description S_436
 * @date 2022-04-17
 */
public class S_436 {


    /**
     * 暴力
     *
     * @param intervals intervals
     * @return return
     */
    public int[] findRightInterval1(int[][] intervals) {
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int min = Integer.MAX_VALUE;
            int minindex = -1;
            for (int j = 0; j < intervals.length; j++) {
                // start[j]>= end[i]
                if (intervals[j][0] >= intervals[i][1] && intervals[j][0] < min) {
                    min = intervals[j][0];
                    minindex = j;
                }
            }
            res[i] = minindex;
        }
        return res;
    }

    public int[] findRightInterval2(int[][] intervals) {
        int[] res = new int[intervals.length];
        Map<int[], Integer> hash = new HashMap<>();
        // 存储数组的对应的下标
        for (int i = 0; i < intervals.length; i++) {
            hash.put(intervals[i], i);
        }
        // 根据start起点排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < intervals.length; i++) {
            int min = Integer.MAX_VALUE;
            int minindex = -1;
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[j][0] >= intervals[i][1] && intervals[j][0] < min) {
                    min = intervals[j][0];
                    minindex = hash.get(intervals[j]);
                }
            }
            res[hash.get(intervals[i])] = minindex;
        }
        return res;
    }
}
