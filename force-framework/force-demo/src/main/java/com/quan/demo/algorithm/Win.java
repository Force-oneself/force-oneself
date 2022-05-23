package com.quan.demo.algorithm;

/**
 * win
 *
 * @author Force-oneself
 * @date 2022-05-21
 */
public class Win {

    public static int win(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    /**
     * 后手拿牌
     *
     * @param arr arr
     * @param l   l
     * @param r   r
     * @return return
     */
    public static int f(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        return Math.max(
                // 先手拿了左边的牌
                arr[l] + s(arr, l + 1, r),
                // 先手拿了右边的牌
                arr[r] + s(arr, l, r - 1)
        );
    }

    /**
     * 后手拿牌
     *
     * @param arr arr
     * @param l   l
     * @param r   r
     * @return return
     */
    public static int s(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        return Math.min(
                f(arr, l + 1, r),
                f(arr, l, r - 1)
        );
    }

}
