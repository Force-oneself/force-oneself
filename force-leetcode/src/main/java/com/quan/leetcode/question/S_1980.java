package com.quan.leetcode.question;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Force-oneself
 * @description S_1980
 * @date 2022-04-13
 */
public class S_1980 {

    Map<String, String> map = new HashMap<>();

    public String findDifferentBinaryString(String[] nums) {
        int len = nums.length;
        for (String num : nums) {
            map.put(num, num);
        }
        return bt(0, len, new StringBuffer());
    }

    public String bt(int begin, int len, StringBuffer sb) {
        if (begin == len) {
            System.out.println(sb);
            return !map.containsKey(sb.toString()) ? sb.toString() : null;
        }
        sb.append("0");
        String str = bt(begin + 1, len, sb);
        if (str != null) {
            return str;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("1");
        str = bt(begin + 1, len, sb);
        if (str != null) {
            return str;
        }
        sb.deleteCharAt(sb.length() - 1);
        return null;
    }

    public static void main(String[] args) {
        new S_1980().findDifferentBinaryString(new String[]{"0000000010", "0000001000", "1111111111", "0000000000", "0000000110", "0000000111", "0000000001", "0000000011", "0000001001", "0000000101"});
    }

}
