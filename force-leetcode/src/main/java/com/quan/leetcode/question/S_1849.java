package com.quan.leetcode.question;

/**
 * @author Force-oneself
 * @description S_1849
 * @date 2022-04-05
 */
public class S_1849 {

    public boolean splitString(String s) {
        // 枚举第一个数字的值，因为s长度为20，所以会超过int，要用long类型
        long t = 0;
        // 因为必须要分割成两个子串，所以最后一个字符不可能是组成第一个数字的字符，我们这里也是为了防止刚好20位导致long也会溢出的情况
        for (int i = 0; i < s.length() - 1; i++) {
            // 把当前字符加入到组成第一个数字的字符集中
            t = t * 10 + s.charAt(i) - '0';
            // 如果t大于10^10那么后面最多还有9位数，所以不可能组成递减的连续值
            if (t > 10000000000L)
                return false;
            // 把t当作第一个数字，找寻后面递减的数
            if (dfs(s, t, i + 1))
                return true;
        }
        return false;
    }

    // s要分割的字符串；pre前面一个数的值；k当前字符串已经用到了哪个位置
    private boolean dfs(String s, long pre, int k) {
        // 代表能组成递减的连续值
        if (k == s.length())
            return true;
        // 枚举pre后面的一个数字的值
        long t = 0;
        // 从第k个字符开始组成数字
        for (int i = k; i < s.length(); i++) {
            t = t * 10 + s.charAt(i) - '0';
            if (t > 10000000000L)
                return false;
            // 如果前面一个数字和当前数组相差为1，则继续往下面寻找满足条件的数组
            if (pre - 1 == t && dfs(s, t, i + 1))
                return true;
            // 当前组成的数大于前面的数表示不符合要求，直接返回false
            if (t >= pre)
                return false;
        }
        return false;
    }

}
