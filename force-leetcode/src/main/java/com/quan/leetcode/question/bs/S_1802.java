package com.quan.leetcode.question.bs;

/**
 * S_1802
 *
 * @author Force-oneself
 * @date 2022-04-29
 */
public class S_1802 {

    public int maxValue(int n, int index, int maxSum) {
        long l = 0, r = maxSum;
        //因为数组中所有的数均为正整数，所以减去n，剩余的表示可以填的数
        maxSum -= n;
        while (l < r) {
            //m表示index指向位置的高度
            long m = l + r + 1 >> 1;

            //计算当index的位置高度为m时，数组所有元素的和
            long count = m * m;
            //如果左边越界，就减去左边多的
            if (m > index) count -= (m - index - 1) * (m - index) / 2;
            //如果右边越界，就减去右边多的
            if (m > (n - index)) count -= (m - (n - index - 1) - 1) * (m - (n - index - 1)) / 2;

            //二分法判断
            if (count > maxSum) r = m - 1;
            else l = m;
        }
        return (int) l + 1;
    }
}
