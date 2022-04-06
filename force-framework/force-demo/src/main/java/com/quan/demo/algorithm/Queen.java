package com.quan.demo.algorithm;

/**
 * @author Force-oneself
 * @description Queen
 * @date 2022-04-05
 */
public class Queen {

    public int num(int n) {
        if (n < 1) {
            return 0;
        }
        int[] records = new int[n];
        return process(0, records, n);
    }

    public int process(int i, int[] records, int n) {
        // 终止返回已找到一种解法
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(records, i, j)) {
                records[i] = j;
                res += process(i + 1, records, n);
            }
        }
        return res;
    }

    private boolean isValid(int[] records, int i, int j) {
        for (int k = 0; k < i; k++) {
            // 不在同一列，不在一个斜线上
            if (j == records[k] || Math.abs(records[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }


    public int bit(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 取该数的低位二进制
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return bitProcess(limit, 0, 0, 0);
    }

    /**
     * ignore
     *
     * @param limit       limit
     * @param colLim      列的限制。1位置可以放皇后，0位置不可以
     * @param leftDiaLIm  左斜线限制，1位置可以放皇后，0位置不可以
     * @param rightDiaLim 右斜线的限制，1位置可以放皇后，0位置不可以
     * @return /
     */
    private int bitProcess(int limit, int colLim, int leftDiaLIm, int rightDiaLim) {
        if (limit == colLim) {
            return 1;
        }
        int mostRightOne;
        // 候选皇后的位置
        int pos = limit & (~(colLim | leftDiaLIm | rightDiaLim));
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos -= mostRightOne;
            res += bitProcess(limit, colLim | mostRightOne, (leftDiaLIm | mostRightOne) << 1, (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }
}
