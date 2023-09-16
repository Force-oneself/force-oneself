package com.quan.demo.algorithm.demo;

/**
 * FindNumInSortedMatrix
 *
 * @author Force-oneself
 * @date 2022-07-13
 */
public class FindNumInSortedMatrix {

    public static boolean isContains(int[][] matrix, int k) {
        // 右上角开始遍历
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col > -1) {
            if (matrix[row][col] == k) {
                return true;
            } else if (matrix[row][col] > k) {
                // 大于k列--
                col--;
            } else {
                // 小于等于k行++
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                // 0
                {0, 1, 2, 3, 4, 5, 6},
                // 1
                {10, 12, 13, 15, 16, 17, 18},
                // 2
                {23, 24, 25, 26, 27, 28, 29},
                // 3
                {44, 45, 46, 47, 48, 49, 50},
                // 4
                {65, 66, 67, 68, 69, 70, 71},
                // 5
                {96, 97, 98, 99, 100, 111, 122},
                // 6
                {166, 176, 186, 187, 190, 195, 200},
                // 7
                {233, 243, 321, 341, 356, 370, 380}
        };
        int k = 233;
        System.out.println(isContains(matrix, k));
    }
}
