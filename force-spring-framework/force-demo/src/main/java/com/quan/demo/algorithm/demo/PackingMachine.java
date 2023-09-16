package com.quan.demo.algorithm.demo;

/**
 * PackingMachine
 * 有n个打包机器从左到右一字排开，上方有一个自动装置会抓取一批放物品到每个打 包机上，放到每个机器上的这些物品数量有多有少，
 * 由于物品数量不相同，需要工人 将每个机器上的物品进行移动从而到达物品数量相等才能打包。每个物品重量太大、 每次只能搬一个物品进行移动，
 * 为了省力，只在相邻的机器上移动。请计算在搬动最 小轮数的前提下，使每个机器上的物品数量相等。如果不能使每个机器上的物品相同， 返回-1。
 * 例如[1,0,5]表示有3个机器，每个机器上分别有1、0、5个物品，经过这些轮后:
 * 第一轮:1    0 <- 5 => 1 1 4 第二轮:1 <- 1 <- 4 => 2 1 3 第三轮:2    1 <- 3 => 2 2 2
 * 移动了3轮，每个机器上的物品相等，所以返回3
 * 例如[2,2,3]表示有3个机器，每个机器上分别有2、2、3个物品， 这些物品不管怎么移动，都不能使三个机器上物品数量相等，返回-1
 *
 * @author Force-oneself
 * @date 2022-07-13
 */
public class PackingMachine {

    public static int minOps(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int size = arr.length;
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        // 不均分
        if (sum % size != 0) {
            return -1;
        }
        int avg = sum / size;
        // 记录i位置左边的原来有的货总和
        int leftSum = 0;
        int ans = 0;
        // 每个位置都求各自的
        for (int i = 0; i < arr.length; i++) {
            // i号机器，是中间机器，左(0~i-1) i 右(i+1~N-1)
            // 负 需要输入     正需要输出
            // a-b 左边给到自己的货
            int leftRest = leftSum - i * avg;
            // 负 需要输入     正需要输出
            // c - d  右边原来的货 - 当前自己的货 - 自己右边平均的货 = 右边给到自己的货
            int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
            // 当前i转移货给左右两边的机器人
            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                // 左右无论哪种情况 都是按照最多需要左右转移的货为最少移动次数
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return ans;
    }

}
