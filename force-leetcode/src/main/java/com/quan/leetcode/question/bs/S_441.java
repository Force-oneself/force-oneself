package com.quan.leetcode.question.bs;

/**
 * @author Force-oneself
 * @description S_441
 * @date 2022-04-18
 */
public class S_441 {

    public int arrangeCoins(int n) {
        int i = 1;
        int level = 0;
        while (n > 0) {
            n -= i;
            level++;
            i++;
        }
        return n == 0 ? level : level - 1;
    }

    /**
     * 数学公式，(( x + 1 ) * x ) / 2 = n ==> x^2 + x - 2n = 0, 取大于0的解：
     *
     * @param n n
     * @return return
     */
    public int arrangeCoins1(int n) {
        return (int) ((Math.sqrt((long) 8 * n + 1) - 1) / 2);
    }


    /**
     * 二分
     *
     * @param n n
     * @return return
     */
    public int arrangeCoins2(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if ((long) mid * (mid + 1) <= (long) 2 * n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(new S_441().arrangeCoins(5));
        System.out.println(new S_441().arrangeCoins(8));
        System.out.println(new S_441().arrangeCoins(10));
    }

}
