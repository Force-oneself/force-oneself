package com.quan.demo.algorithm.demo;

/**
 * MakeNo
 *
 * @author Force-oneself
 * @date 2022-07-04
 */
public class MakeNo {

    /**
     * 生成长度为size的达标数组
     * 达标：对于任意的 i<k<j，满足   [i] + [j]  != [k] * 2
     *
     * @param size size
     * @return /
     */
    public static int[] makeNo(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        // size
        // 一半长达标来
        // 7   :   4
        // 8   :   4
        // [4个奇数] [3个偶]
        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        // base -> 等长奇数达标来
        // base -> 等长偶数达标来
        int[] ans = new int[size];
        int index = 0;
        for (; index < halfSize; index++) {
            ans[index] = base[index] * 2 - 1;
        }
        for (int i = 0; index < size; index++, i++) {
            ans[index] = base[i] * 2;
        }
        return ans;
    }


    /**
     * 检验函数
     *
     * @param arr null
     * @return /
     */
    public static boolean isValid(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int k = i + 1; k < len; k++) {
                for (int j = k + 1; j < len; j++) {
                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int len = 1; len < 1000; len++) {
            int[] arr = makeNo(len);
            if (!isValid(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");

        System.out.println(isValid(makeNo(1042)));
        System.out.println(isValid(makeNo(2981)));
    }
}
