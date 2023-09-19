package com.quan.leetcode.question.string;

import java.util.Collections;
import java.util.List;

/**
 * S_539
 *
 * @author Force-oneself
 * @date 2022-05-29
 */
public class S_539 {

    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        int t0Minutes = getMinutes(timePoints.get(0));
        int preMinutes = t0Minutes;
        for (int i = 1; i < timePoints.size(); ++i) {
            int minutes = getMinutes(timePoints.get(i));
            // 相邻时间的时间差
            ans = Math.min(ans, minutes - preMinutes);
            preMinutes = minutes;
        }
        // 首尾时间的时间差
        ans = Math.min(ans, t0Minutes + 1440 - preMinutes);
        return ans;
    }

    public int getMinutes(String t) {
        return ((t.charAt(0) - '0') * 10 + (t.charAt(1) - '0')) * 60 + (t.charAt(3) - '0') * 10 + (t.charAt(4) - '0');
    }

    public int findMinDifference1(List<String> timePoints) {
        int n = timePoints.size();
        // 鸽巢原理 24×60=1440 超出即会出现两个相同时间，此时可以直接返回0
        if (n > 1440) {
            return 0;
        }
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        int t0Minutes = getMinutes(timePoints.get(0));
        int preMinutes = t0Minutes;
        for (int i = 1; i < n; ++i) {
            int minutes = getMinutes(timePoints.get(i));
            // 相邻时间的时间差
            ans = Math.min(ans, minutes - preMinutes);
            preMinutes = minutes;
        }
        // 首尾时间的时间差
        ans = Math.min(ans, t0Minutes + 1440 - preMinutes);
        return ans;
    }

}
