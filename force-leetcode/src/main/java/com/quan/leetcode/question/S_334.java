package com.quan.leetcode.question;

/**
 * @author Force-oneself
 * @description S_334
 * @date 2022-04-07
 */
public class S_334 {

    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(new S_334().increasingTriplet(new int[]{2, 1, 5, 0, 4, 6}));
//        System.out.println(new S_334().increasingTriplet(new int[]{2,4,-2,-3}));
//        System.out.println(new S_334().increasingTriplet(new int[]{20,100,10,12,5,13}));
        System.out.println(new S_334().increasingTriplet(new int[]{1, 5, 0, 4, 1, 3}));
    }

}
