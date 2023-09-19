package com.quan.leetcode.question.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * S_151
 *
 * @author Force-oneself
 * @date 2022-05-21
 */
public class S_151 {

    public String reverseWords1(String s) {
        String[] split = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if ("".equals(split[i])) {
                continue;
            }
            sb.append(split[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    public static void main(String[] args) {
        System.out.println(new S_151().reverseWords("the sky is blue"));
        System.out.println(new S_151().reverseWords("  hello world  "));
        System.out.println(new S_151().reverseWords("a good   example"));
    }

}
