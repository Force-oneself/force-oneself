package com.quan.leetcode.question.binarysearch;

/**
 * S_744
 *
 * @author Force-oneself
 * @date 2022-04-20
 */
public class S_744 {

    public char nextGreatestLetter(char[] letters, char target) {
        int length = letters.length;
        if (target >= letters[length - 1]) {
            return letters[0];
        }
        int low = 0, high = length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (letters[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return letters[low];
    }

    public static void main(String[] args) {
        System.out.println(new S_744().nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'c'));
        System.out.println(new S_744().nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'e'));
    }
}
