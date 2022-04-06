package com.quan.leetcode.question;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description S_1718
 * @date 2022-04-05
 */
public class S_1718 {

    public int[] constructDistancedSequence(int n) {
        int[] res = new int[2 * n - 1];
        Arrays.fill(res, -1);
        backtrack(0, n, new boolean[n], res);
        return res;
    }

    public int[] backtrack(int pos, int n, boolean[] visited, int[] res) {
        // 结束条件
        if (pos == res.length) {
            return res;
        }
        if (res[pos] != -1) {
            return backtrack(pos + 1, n, visited, res);
        }
        for (int i = n; i >= 1; i--) {
            // 是否已经被访问过
            if (visited[i - 1]) {
                continue;
            }
            // 是否越界
            if (i > 1 && pos + i >= res.length) {
                continue;
            }
            // pos+i位置是否已经被使用过
            if (i > 1 && res[pos + i] != -1) {
                continue;
            }
            if (i == 1) {
                res[pos] = i;
            } else {
                res[pos] = res[pos + i] = i;
            }
            visited[i - 1] = true;
            int[] r = backtrack(pos + 1, n, visited, res);
            if (r != null) {
                return r;
            }
            if (i == 1) {
                res[pos] = -1;
            } else {
                res[pos] = res[pos + i] = -1;
            }
            visited[i - 1] = false;
        }
        return null;
    }

}
