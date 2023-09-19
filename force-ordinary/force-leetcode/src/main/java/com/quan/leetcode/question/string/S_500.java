package com.quan.leetcode.question.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * S_500
 *
 * @author Force-oneself
 * @date 2022-05-27
 */
public class S_500 {

    int f = mark("qwertyuiop");
    int s = mark("asdfghjkl");
    int t = mark("zxcvbnm");

    public String[] findWords(String[] words) {
        List<String> sb = new ArrayList<>();
        for (String word : words) {
            boolean match = match(f, word) || match(s, word) || match(t, word);
            if (match) {
                sb.add(word);
            }
        }
        String[] ant = new String[sb.size()];
        for (int i = 0; i < ant.length; i++) {
            ant[i] = sb.get(i);
        }
        return ant;
    }

    private boolean match(int f, String word) {
        for (int i = 0; i < word.length(); i++) {
            if ((1 << (word.charAt(i) - 'a') & f) == 0) {
                return false;
            }
        }
        return true;
    }

    public int mark(String str) {
        int mark = 0;
        for (int i = 0; i < str.length(); i++) {
            mark |= 1 << (str.charAt(i) - 'a');
        }
        return mark;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new S_500().findWords(new String[]{"Hello","Alaska","Dad","Peace"})));
        System.out.println(Arrays.toString(new S_500().findWords(new String[]{"omk"})));
        System.out.println(Arrays.toString(new S_500().findWords(new String[]{"adsdf","sfd"})));
    }
}
