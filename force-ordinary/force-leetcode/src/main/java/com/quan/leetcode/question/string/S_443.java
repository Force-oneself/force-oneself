package com.quan.leetcode.question.string;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * S_443
 *
 * @author Force-oneself
 * @date 2022-05-26
 */
public class S_443 {

    public int compress1(char[] chars) {
        Deque<Character> queue = new LinkedList<>();

        int counter = 1;
        for (char c : chars) {
            if (queue.isEmpty() || queue.peekLast() != c) {
                getCounter(queue, counter);
                queue.add(c);
                counter = 1;
                continue;
            }
            counter++;
        }
        getCounter(queue, counter);
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            chars[i] = queue.pollFirst();
        }
        return size;
    }

    private void getCounter(Deque<Character> queue, int counter) {
        if (counter <= 1) {
            return;
        }
        List<Integer> count = new ArrayList<>();
        while (counter > 0) {
            count.add(counter % 10);
            counter /= 10;
        }
        for (int i = count.size() - 1; i >= 0; i--) {
            queue.add((char) (count.get(i) + '0'));
        }
    }

    /**
     * 双指针
     *
     * @param chars chars
     * @return return
     */
    public int compress(char[] chars) {
        int n = chars.length;
        int write = 0, left = 0;
        for (int read = 0; read < n; read++) {
            if (read != n - 1 && chars[read] == chars[read + 1]) {
                continue;
            }
            chars[write++] = chars[read];
            int num = read - left + 1;
            if (num > 1) {
                int anchor = write;
                while (num > 0) {
                    chars[write++] = (char) (num % 10 + '0');
                    num /= 10;
                }
                reverse(chars, anchor, write - 1);
            }
            left = read + 1;
        }
        return write;
    }

    public void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new S_443().compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
        System.out.println(new S_443().compress(new char[]{'a'}));
        System.out.println(new S_443().compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
    }
}
