package com.quan.leetcode.question.bs;

/**
 * @author Force-oneself
 * @description S_374
 * @date 2022-04-17
 */
public class S_374 {

    private final Integer pick;

    public S_374(int guess) {
        this.pick = guess;
    }


    public int guessNumber(int n) {
        int l = 1;
        int r = n;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            int guess = guess(mid);
            if (guess == 0) {
                return mid;
            } else if (guess == 1) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public int guess(int n) {
        return this.pick.compareTo(n);
    }
}
