package com.quan.leetcode.question.sw;

/**
 * S_978
 *
 * @author Force-oneself
 * @date 2022-05-01
 */
public class S_978 {

    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int ret = 1;
        int left = 0, right = 0;

        while (right < n - 1) {
            // 开始时左右在0
            if (left == right) {
                // 如果下一个相等即当前值无效构成川流数组，则left右移
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                // 湍流数组的定义两个条件，满足则right右移扩大数组长度，否则left需要从right开始重新计算
                if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                    right++;
                } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                    right++;
                } else {
                    left = right;
                }
            }
            ret = Math.max(ret, right - left + 1);
        }
        return ret;
    }
}
