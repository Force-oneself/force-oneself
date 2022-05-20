package com.quan.leetcode.question.string;

/**
 * S_58
 *
 * @author Force-oneself
 * @date 2022-05-20
 */
public class S_58 {

    public int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int ant = 0;
        boolean matchBlank  = false;
        for (int i = len -1; i >= 0; i--) {
            if (!matchBlank && chars[i] == ' ') {
                continue;
            }
            if (matchBlank && chars[i] == ' ') {
                return ant;
            }
            matchBlank = true;
            ant++;
        }
        return ant;
    }

    public static void main(String[] args) {
        System.out.println(new S_58().lengthOfLastWord("Hello World"));
        System.out.println(new S_58().lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(new S_58().lengthOfLastWord("luffy is still joyboy"));
    }
}
