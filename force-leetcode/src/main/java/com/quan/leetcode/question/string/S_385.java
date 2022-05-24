package com.quan.leetcode.question.string;

import java.util.List;

/**
 * S_385
 *
 * @author Force-oneself
 * @date 2022-05-24
 */
public class S_385 {

    int index = 0;

    public NestedInteger deserialize(String s) {
        if (s.charAt(index) == '[') {
            index++;
            NestedInteger ni = new NestedInteger();
            while (s.charAt(index) != ']') {
                ni.add(deserialize(s));
                if (s.charAt(index) == ',') {
                    index++;
                }
            }
            index++;
            return ni;
        } else {
            boolean negative = false;
            if (s.charAt(index) == '-') {
                negative = true;
                index++;
            }
            int num = 0;
            while (index < s.length() && Character.isDigit(s.charAt(index))) {
                num = num * 10 + s.charAt(index) - '0';
                index++;
            }
            if (negative) {
                num *= -1;
            }
            return new NestedInteger(num);
        }
    }

    public class NestedInteger {

        public NestedInteger() {
        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return false;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return 0;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return null;
        }
    }

}
