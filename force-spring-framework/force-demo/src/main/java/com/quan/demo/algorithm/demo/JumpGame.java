package com.quan.demo.algorithm.demo;

/**
 * JumpGame
 * 给出一组正整数arr，你从第0个数向最后一个数，
 * 每个数的值表示你从这个位置可以向右跳跃的最大长度
 * 计算如何以最少的跳跃次数跳到最后一个数。
 *
 * @author Force-oneself
 * @date 2022-07-21
 */
public class JumpGame {


    public static int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 跳了多少步
        int step = 0;
        // step步内，右边界
        int cur = 0;
        // step+1步内，右边界
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            // 当前可达右边界已越界
            if (cur < i) {
                // 步数增加
                step++;
                cur = next;
            }
            // 可达右边界
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 3, 1, 1, 4};
        System.out.println(jump(arr));

    }
}
