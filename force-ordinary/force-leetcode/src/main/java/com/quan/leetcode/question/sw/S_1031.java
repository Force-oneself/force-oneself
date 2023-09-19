package com.quan.leetcode.question.sw;

/**
 * S_1031
 *
 * @author Force-oneself
 * @date 2022-05-01
 */
public class S_1031 {


    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int lRight = firstLen - 1;
        int lSum = 0, mInitSum = 0;

        // 数组L的和
        for (int i = 0; i < firstLen - 1; i++) {
            lSum += nums[i];
        }
        // 数组M的和
        for (int i = 0; i < secondLen - 1; i++) {
            mInitSum += nums[i];
        }

        int result = -1;
        int len = nums.length;
        for (int lLeft = 0; lRight < len; lLeft++, lRight++) {
            lSum += nums[lRight];
            int mRight = secondLen - 1;
            int mSum = mInitSum;
            for (int mLeft = 0; mRight < len; mLeft++, mRight++) {
                mSum += nums[mRight];
                if (mRight < lLeft || mLeft > lRight) {
                    if (mSum + lSum > result) {
                        result = mSum + lSum;
                    }
                }
                mSum -= nums[mLeft];
            }
            lSum -= nums[lLeft];
        }
        return result;
    }
}
