package com.quan.leetcode.question.string;

/**
 * S_657
 *
 * @author Force-oneself
 * @date 2022-05-31
 */
public class S_657 {

    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        int len = moves.length();
        for (int i = 0; i < len; i++) {
            char m = moves.charAt(i);
            if (m == 'L') {
                x--;
            }
            if (m == 'R') {
                x++;
            }
            if (m == 'U') {
                y++;
            }
            if (m == 'D') {
                y--;
            }
        }
        return x == 0 && y == 0;
    }

    public static void main(String[] args) {
        System.out.println(new S_657().judgeCircle("UD"));
        System.out.println(new S_657().judgeCircle("LL"));
    }
}
