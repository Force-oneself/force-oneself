package com.quan.leetcode.question.binarysearch;

/**
 * @author Force-oneself
 * @description S_367
 * @date 2022-04-17
 */
public class S_367 {

    public boolean isPerfectSquare(int num) {
        int left = 0, right = num;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            long square = (long) mid * mid;
            if (square < num) {
                left = mid + 1;
            } else if (square > num) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

}
