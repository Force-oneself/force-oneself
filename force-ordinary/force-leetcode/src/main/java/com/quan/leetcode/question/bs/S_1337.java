package com.quan.leetcode.question.bs;

import java.util.*;

/**
 * S_1337
 *
 * @author Force-oneself
 * @date 2022-04-24
 */
public class S_1337 {

    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        List<int[]> power = new ArrayList<int[]>();
        for (int i = 0; i < m; ++i) {
            int l = 0, r = n - 1, pos = -1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (mat[i][mid] == 0) {
                    r = mid - 1;
                } else {
                    pos = mid;
                    l = mid + 1;
                }
            }
            power.add(new int[]{pos + 1, i});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

            @Override
            public int compare(int[] pair1, int[] pair2) {
                if (pair1[0] != pair2[0]) {
                    return pair1[0] - pair2[0];
                } else {
                    return pair1[1] - pair2[1];
                }
            }
        });
        for (int[] pair : power) {
            pq.offer(pair);
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = pq.poll()[1];
        }
        return ans;
    }

    public int[] kWeakestRows1(int[][] mat, int k) {
        int[] list = new int[mat.length];
        int[] result = new int[k];
        for (int i = 0; i < mat.length; i++) {
            list[i] = count(mat[i]) * 100 + i;
        }
        Arrays.sort(list);
        for (int i = 0; i < k; i++) {
            result[i] = list[i] % 100;
        }
        return result;
    }

    public int count(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            if (n != 1) {
                break;
            }
            sum += n;
        }
        return sum;
    }

}
