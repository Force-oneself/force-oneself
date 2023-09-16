package com.quan.demo.algorithm.demo;

/**
 * ColorLeftRight
 *
 * @author Force-oneself
 * @date 2022-07-04
 */
public class ColorLeftRight {

    /**
     * RGRGR -> RRRGG
     *
     * @param s s
     * @return  /
     */
    public static int minPaint(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int len = str.length;
        int rAll = 0;
        for (char c : str) {
            rAll += c == 'R' ? 1 : 0;
        }
        // 如果数组所有的范围，都是右侧范围，都变成G
        int ans = rAll;
        int left = 0;
        // 0..i 左侧 n-1..len-1
        for (int i = 0; i < len - 1; i++) {
            left += str[i] == 'G' ? 1 : 0;
            rAll -= str[i] == 'R' ? 1 : 0;
            ans = Math.min(ans, left + rAll);
        }
        // 0...len-1 左全部 右无
        ans = Math.min(ans, left + (str[len - 1] == 'G' ? 1 : 0));
        return ans;
    }

    public static void main(String[] args) {
        String test = "GGGGGR";
        System.out.println(minPaint(test));
    }

}
