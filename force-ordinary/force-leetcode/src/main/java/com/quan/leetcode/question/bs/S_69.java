package com.quan.leetcode.question.bs;

/**
 * @author Force-oneself
 * @description S_69
 * @date 2022-04-14
 */
public class S_69 {

    public int mySqrt1(int x) {
        int l = 0;
        int r = x;
        while (l <= r) {
            int mid = (l + r) / 2;
            int plus = mid * mid;
            if (plus == x) return mid;
            else if (plus > x) r = mid - 1;
            else l = mid + 1;
        }
        return l - 1;
    }

    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
//        System.out.println(new S_69().mySqrt(8));
//        System.out.println(new S_69().mySqrt(4));
//        System.out.println(new S_69().mySqrt(24));
//        System.out.println(new S_69().mySqrt(0));
        System.out.println(new S_69().mySqrt(2147395599));
    }
}
