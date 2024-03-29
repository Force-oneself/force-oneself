package com.quan.leetcode.question.dp;

import java.util.Arrays;
import java.util.HashMap;

/**
 * S_464
 *
 * @author Force-oneself
 * @date 2022-05-18
 */
public class S_464 {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) return true;
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;

        return dfs(0, desiredTotal, new Boolean[1 << maxChoosableInteger], maxChoosableInteger);
    }

    private boolean dfs(int state, int desiredTotal, Boolean[] dp, int maxChoosableInteger) {
        if (dp[state] != null) {
            return dp[state];
        }

        for (int i = 1; i <= maxChoosableInteger; i++) {
            int cur = 1 << (i - 1);
            if ((cur & state) != 0) {
                continue;
            }

            if (i >= desiredTotal || !dfs(cur | state, desiredTotal - i, dp, maxChoosableInteger)) {
                return dp[state] = true;
            }
        }
        return dp[state] = false;
    }


    /**
     * 记忆化回溯（也称为递归+备忘录），自顶向下
     * 采用记忆化后的时间复杂度为O(2^n)(如果不进行记忆的话，时间复杂度将是O(n!))，可以理解为已经缩成了只有一个分支了
     * 然后为什么要进行记忆化：
     * 因为我们发现，例如[2,3]和[3,2]之后的玩家选择状态都是一样的，都是可以从除了2,3之外的
     * 数字进行选择，那么就可以对选择2和3后第一个玩家能不能赢进行记忆存储
     * 这里采用state[]数组存储每个数字是否都被选过，选过则记录为1，然后我们将state.toString()
     * 使得[2,3]和[3,2]它们的结果都是一样的"0011"，作为key，存储在HashMap中，value是选了2和3
     * 之后第一个玩家是否稳赢
     *
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) return true;
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            //1,..maxChoosable数列总和都比目标和小
            return false;
        }
        //state[1]=1表示1被选了
        int[] state = new int[maxChoosableInteger + 1];

        return backtraceWitMemo(state, desiredTotal, new HashMap<String, Boolean>());
    }

    private boolean backtraceWitMemo(int[] state, int desiredTotal, HashMap<String, Boolean> map) {
        //这里比较关键，如何表示这个唯一的状态，例如[2,3]和[3,2]都是"0011"，状态一样
        String key = Arrays.toString(state);
        //如果已经记忆了这样下去的输赢结果,记忆是为了防止如[2,3]，[3,2]之后的[1,4,5,..]这个选择区间被重复计算
        if (map.containsKey(key)) return map.get(key);

        for (int i = 1; i < state.length; i++) {
            //如果这个数字i还没有被选中
            if (state[i] == 0) {
                state[i] = 1;
                //如果当前选了i已经赢了或者选了i还没赢但是后面对方选择输了
                if (desiredTotal - i <= 0 || !backtraceWitMemo(state, desiredTotal - i, map)) {
                    map.put(key, true);
                    //在返回之前回溯
                    state[i] = 0;
                    return true;
                }
                //如果不能赢也要记得回溯
                state[i] = 0;
            }
        }
        //如果都赢不了
        map.put(key, false);
        return false;
    }

    /**
     * @Description: 由于状态不可用数组进行传递【在递归当中会受到改变，不能准确定位当前状态】，故在此处用Int的位表示状态（1表示用过,0表示未用过）
     * 这里采用DP状态压缩的方式，思想与回溯类似，只是这里的状态被压缩成了一个bitArray了
     * 状态压缩，我们可以用二进制的第i位的0或者1来表示i这个数字的选取与否，这样所有数字的选取状态就可以用一个数来很方便的表示，
     * 题目说了不超过20位，所以这里就可以用一个int来表示状态state，通过state来判断状态是否一致，进而进行记忆化的存取
     */
    public boolean canIWin3(int maxChoosableInteger, int desiredTotal) {

        if (maxChoosableInteger >= desiredTotal) return true;
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        /**
         *  dp表示"每个"取数(A和B共同作用的结果)状态下的输赢
         *  例如只有1,2两个数选择，那么 (1 << 2) - 1 = 4 - 1 = 3种状态表示：
         *  01,10,11分别表示：A和B已经选了1，已经选了2，已经选了1、2状态下，A的输赢情况
         *  并且可见这个表示所有状态的dp数组的每个状态元素的长度为maxChoosableInteger位的二进制数
         */
        Boolean[] dp = new Boolean[(1 << maxChoosableInteger) - 1];
        return dfs(maxChoosableInteger, desiredTotal, 0, dp);
    }

    /**
     * @param maxChoosableInteger 选择的数的范围[1,2,...maxChoosableInteger]
     * @param desiredTotal        目标和
     * @param state               当前状态的十进制表示（记录着可能不止一个数被选择的状态）
     * @param dp                  记录所有状态
     * @return
     */
    private boolean dfs(int maxChoosableInteger, int desiredTotal, int state, Boolean[] dp) {
        if (dp[state] != null)
            return dp[state];
        /**
         * 例如maxChoosableInteger=2，选择的数只有1,2两个，二进制只要两位就可以表示他们的选择状态
         * 最大位为2（第2位），也就是1 << (2 - 1)的结果，所以最大的位可以表示为  1 << (maxChoosableInteger - 1)
         * 最小的位可以表示为 1 << (1 - 1)，也就是1（第1位）
         * 这里i表示括号的范围
         */
        for (int i = 1; i <= maxChoosableInteger; i++) {
            //当前待抉择的位，这里的tmp十进制只有一位为1，用来判断其为1的位，对于state是否也是在该位上为1
            //用以表示该位（数字）是否被使用过
            /**
             * (&运算规则，都1才为1)
             * 例如,i=3, tmp = 4, state = 3;
             *  100
             * &011
             * =0  表示该位没有被使用过，也就是第三位没有被使用过，即数字3 (i)没有被使用过
             */
            int tmp = (1 << (i - 1));
            if ((tmp & state) == 0) {  //该位没有被使用过
                //如果当前选了i已经赢了或者选了i还没赢但是后面对方选择输了,tmp|state表示进行状态的更新
                /**
                 * 例如
                 *  100
                 * |011
                 * =111
                 */
                //注意这里并没有像回溯一样进行状态的(赋值化的)更新、回溯
                //其实这里是隐含了回溯的，我们通过参数传递更新后的state
                //但是我们在这个调用者这里的state还是没有进行更新的，所以
                //就相当于回溯了状态。
                if (desiredTotal - i <= 0 || !dfs(maxChoosableInteger, desiredTotal - i, tmp | state, dp)) {
                    dp[state] = true;
                    return true;
                }
            }
        }
        //如果都赢不了
        dp[state] = false;
        return false;
    }
}
