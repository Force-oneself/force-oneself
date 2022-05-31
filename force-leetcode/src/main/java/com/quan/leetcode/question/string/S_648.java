package com.quan.leetcode.question.string;

import java.util.*;

/**
 * S_648
 *
 * @author Force-oneself
 * @date 2022-05-31
 */
public class S_648 {

    public String replaceWords(List<String> dictionary, String sentence) {
        dictionary.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String s : sentence.split(" ")) {
            boolean match = false;
            for (String d : dictionary) {
                if (s.startsWith(d)) {
                    sb.append(d);
                    match = true;
                    break;
                }
            }
            if (!match) {
                sb.append(s);
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * 前缀树
     *
     * @param roots    roots
     * @param sentence sentence
     * @return return
     */
    public String replaceWords1(List<String> roots, String sentence) {
        TrieNode trie = new TrieNode();
        for (String root : roots) {
            TrieNode cur = trie;
            for (char letter : root.toCharArray()) {
                if (cur.children[letter - 'a'] == null)
                    cur.children[letter - 'a'] = new TrieNode();
                cur = cur.children[letter - 'a'];
            }
            cur.word = root;
        }

        StringBuilder ans = new StringBuilder();

        for (String word : sentence.split("\\s+")) {
            if (ans.length() > 0)
                ans.append(" ");

            TrieNode cur = trie;
            for (char letter : word.toCharArray()) {
                if (cur.children[letter - 'a'] == null || cur.word != null)
                    break;
                cur = cur.children[letter - 'a'];
            }
            ans.append(cur.word != null ? cur.word : word);
        }
        return ans.toString();
    }

    class TrieNode {
        TrieNode[] children;
        String word;

        TrieNode() {
            children = new TrieNode[26];
        }

    }

    /**
     * hash
     *
     * @param roots    roots
     * @param sentence sentence
     * @return return
     */
    public String replaceWords2(List<String> roots, String sentence) {
        Set<String> rootset = new HashSet<>(roots);

        StringBuilder ans = new StringBuilder();
        for (String word : sentence.split("\\s+")) {
            String prefix = "";
            for (int i = 1; i <= word.length(); ++i) {
                prefix = word.substring(0, i);
                if (rootset.contains(prefix)) break;
            }
            if (ans.length() > 0) ans.append(" ");
            ans.append(prefix);
        }
        return ans.toString();
    }


    public static void main(String[] args) {
        System.out.println(new S_648().replaceWords(Arrays.asList("cat", "bat", "rat"), "the cattle was rattled by the battery"));
        System.out.println(new S_648().replaceWords(Arrays.asList("a", "b", "c"), "aadsfasf absbs bbab cadsfafs"));
    }
}
