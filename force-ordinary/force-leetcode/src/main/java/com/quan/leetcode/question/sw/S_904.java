package com.quan.leetcode.question.sw;

/**
 * S_904
 *
 * @author Force-oneself
 * @date 2022-05-01
 */
public class S_904 {

    /**
     * 暴力
     *
     * @param fruits fruits
     * @return return
     */
    public int totalFruit(int[] fruits) {
        int len = fruits.length;
        int ans = 1;
        for (int i = 1; i < len; i++) {
            int left = -1;
            int index = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (fruits[i] == fruits[j] || left == -1 || left == fruits[j]) {
                    if (left == -1 && fruits[i] != fruits[j]) {
                        left = fruits[j];
                    }
                    index = j;
                } else {
                    break;
                }
            }
            ans = Math.max(ans, i - index + 1);
        }
        return ans;
    }


    /**
     * 滑动窗口
     *
     * @param tree tree
     * @return return
     */
    public int totalFruit1(int[] tree) {
        if (tree.length == 0) {
            return 0;
        }
        int len = 0, first = 0, second = 0, temp = 0;
        for (int i = 0; i < tree.length; i++) {
            // 判断是否出现第三个篮子
            if (tree[i] != tree[first] && tree[i] != tree[second]) {
                // 只有初始化时,第一个篮子和第二个篮子都为0时才相等
                // 相等时出现的是第二个篮子,故不用更新first,只需要更新second
                // 不相等时出现的是第三个篮子,故first,second都需要更新
                if (first != second) {
                    first = temp;
                }
                second = i;
            }
            // 更新len最大值
            len = Math.max(len, i - first + 1);
            // 计算temp
            if (tree[temp] != tree[i]) {
                temp = i;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(new S_904().totalFruit(new int[]{1, 2, 1}));
        System.out.println(new S_904().totalFruit(new int[]{0, 1, 2, 1}));
        System.out.println(new S_904().totalFruit(new int[]{1, 2, 3, 2, 2}));
        System.out.println(new S_904().totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));
    }
}
