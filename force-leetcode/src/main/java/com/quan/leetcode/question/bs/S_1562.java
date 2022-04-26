package com.quan.leetcode.question.bs;

import java.util.Arrays;

/**
 * S_1562
 *
 * @author Force-oneself
 * @date 2022-04-26
 */
public class S_1562 {

    public int findLatestStep2(int[] arr, int m) {

        // 段：1 1 1 1 1 1
        // 核心是1个指针定义段：如果是段头就指向段尾，如果是段尾就指向段头，段长度 = abs(link[i] - i) + 1

        // link数组：头尾各有一个空点，所以是大小是 n + 2：_ 1 2 3 4 5 _
        int[] link = new int[arr.length + 2];

        // 段长为 m 的个数
        int cnt = 0;
        int res = -1;

        for (int i = 0; i < arr.length; i++) {

            int x = arr[i];
            // 左段 link
            int l = link[x - 1] != 0 ? link[x - 1] : x;
            // 右段 link
            int r = link[x + 1] != 0 ? link[x + 1] : x;

            // 修改 cnt
            if (x - l == m) {
                cnt--;
            }
            if (r - x == m) {
                cnt--;
            }
            if (r - l + 1 == m) {
                cnt++;
            }
            if (cnt > 0) {
                res = i + 1;
            }

            // 合并段
            link[l] = r;
            link[r] = l;
        }
        return res;
    }


    public int findLatestStep1(int[] arr, int m) {
        int n = arr.length;
        int[][] endpoints = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(endpoints[i], -1);
        }
        int cnt = 0;
        int ret = -1;

        for (int i = 0; i < n; i++) {
            int left = arr[i], right = arr[i];
            //
            if (arr[i] > 1 && endpoints[arr[i] - 1][0] != -1) {
                left = endpoints[arr[i] - 1][0];
                int leftLength = endpoints[arr[i] - 1][1] - endpoints[arr[i] - 1][0] + 1;
                if (leftLength == m) {
                    cnt--;
                }
            }
            //
            if (arr[i] < n && endpoints[arr[i] + 1][1] != -1) {
                right = endpoints[arr[i] + 1][1];
                int rightLength = endpoints[arr[i] + 1][1] - endpoints[arr[i] + 1][0] + 1;
                if (rightLength == m) {
                    cnt--;
                }
            }
            int length = right - left + 1;
            if (length == m) {
                cnt++;
            }
            if (cnt > 0) {
                ret = i + 1;
            }
            endpoints[left][0] = endpoints[right][0] = left;
            endpoints[left][1] = endpoints[right][1] = right;
        }
        return ret;
    }

    /**
     * 穷举
     *
     * @param arr arr
     * @param m   m
     * @return return
     */
    public int findLatestStep(int[] arr, int m) {
        int len = arr.length;

        int[] str = new int[len];
        int step = 0;
        int last = -1;
        for (int j : arr) {
            step++;
            if (step < m) {
                continue;
            }
            str[j - 1] = 1;
            boolean match = match(str, m);
            if (match) {
                last = step;
            }
        }
        return last;
    }

    private boolean match(int[] str, int m) {
        int pre = 0;
        int len = str.length;
        for (int i = 0; i < len; i++) {
            if (str[i] == 0) {
                if (pre == m) {
                    return true;
                }
                if (m > len - i + 1) {
                    return false;
                }
                pre = 0;
                continue;
            }
            pre++;
        }
        return pre == m;
    }

    public static void main(String[] args) {
        System.out.println(new S_1562().findLatestStep(new int[]{3, 1, 5, 4, 2}, 2));
//        System.out.println(new S_1562().findLatestStep(new int[]{3, 5, 1, 2, 4}, 1));
    }
}
