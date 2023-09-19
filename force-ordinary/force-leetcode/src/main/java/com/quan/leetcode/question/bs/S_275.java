package com.quan.leetcode.question.bs;

/**
 * @author Force-oneself
 * @description S_275
 * @date 2022-04-16
 */
public class S_275 {

    public int hIndex(int[] citations) {
        int len = citations.length;
        int l = 0;
        int r = len - 1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (citations[mid] >= len - mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return len - l;
    }

    public static void main(String[] args) {
        System.out.println(new S_275().hIndex(new int[]{0, 1, 3, 5, 6}));
        System.out.println(new S_275().hIndex(new int[]{1, 2, 100}));
        System.out.println(new S_275().hIndex(new int[]{1, 1, 100}));
    }
}
