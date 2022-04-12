package com.quan.leetcode.question;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description S_826
 * @date 2022-04-12
 */
public class S_826 {

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int len = difficulty.length;
        int[][] job = new int[len][2];
        for (int i = 0; i < len; i++) {
            job[i] = new int[]{difficulty[i], profit[i]};
        }
        // 按难度排序
        Arrays.sort(job, (a, b) -> a[0] - b[0]);
        Arrays.sort(worker);


        int ans = 0, i = 0, best = 0;
        for (int skill : worker) {
            while (i < len && skill >= job[i][0])
                best = Math.max(best, job[i++][1]);
            ans += best;
        }

        return ans;
    }
}
