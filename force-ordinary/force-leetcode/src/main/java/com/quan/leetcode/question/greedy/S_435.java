package com.quan.leetcode.question.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Force-oneself
 * @description S_435
 * @date 2022-04-07
 */
public class S_435 {

    class Area {
        Integer start;
        Integer end;

        public Area(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        List<Area> areas = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            areas.add(new Area(intervals[i][0], intervals[i][1]));
        }
        areas.sort(new Comparator<Area>() {
            @Override
            public int compare(Area o1, Area o2) {
                return o1.end.compareTo(o2.end);
            }
        });
        int s = 0;
        int res = 0;
        for (Area area : areas) {
            if (area.start >= s) {
                res++;
                s = area.end;
            }
        }

        return areas.size() - res;
    }

    public int eraseOverlapIntervals1(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int res = 0;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end <= intervals[i][0])
                end = intervals[i][1];
            else {
                end = Math.min(end, intervals[i][1]);
                res++;
            }
        }

        return res;
    }

}
