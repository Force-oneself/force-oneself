package com.quan.leetcode.question;

/**
 * @author Force-oneself
 * @description S_670
 * @date 2022-04-10
 */
public class S_670 {

    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        boolean match = false;
        for (int i = 0; i < chars.length; i++) {
            int max = chars[i];
            int idx = i;
            for (int j = chars.length - 1; j > i; j--) {
                if (chars[j] > max) {
                    max = chars[j];
                    idx = j;
                }
            }
            if (i != idx) {
                int tmp = chars[i];
                chars[i] = chars[idx];
                chars[idx] = (char) tmp;
                break;
            }
        }

        return Integer.parseInt(String.copyValueOf(chars));

    }


    public int maximumSwap1(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        char[] charArray = s.toCharArray();

        // 记录每个数字出现的最后一次出现的下标
        int[] last = new int[10];
        for (int i = 0; i < len; i++) {
            last[charArray[i] - '0'] = i;
        }

        // 从左向右扫描，找到当前位置右边的最大的数字并交换
        for (int i = 0; i < len - 1; i++) {
            // 找最大，所以倒着找
            for (int d = 9; d > charArray[i] - '0'; d--) {
                if (last[d] > i) {
                    swap(charArray, i, last[d]);
                    // 只允许交换一次，因此直接返回
                    return Integer.parseInt(new String(charArray));
                }
            }
        }
        return num;
    }

    private void swap(char[] charArray, int index1, int index2) {
        char temp = charArray[index1];
        charArray[index1] = charArray[index2];
        charArray[index2] = temp;
    }

    public static void main(String[] args) {
//        System.out.println(new S_670().maximumSwap(2736));
//        System.out.println(new S_670().maximumSwap(9973));
        System.out.println(new S_670().maximumSwap(1993));
//        System.out.println(new S_670().maximumSwap(98368));
    }
}
