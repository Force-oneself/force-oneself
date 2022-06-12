package com.quan.demo.algorithm.expand.matrix;

/**
 * RotateMatrix 根据中心顺时针90度旋转矩形
 *
 * @author Force-oneself
 * @date 2022-06-12
 */
public class RotateMatrix {

    /**
     * b   d
     * a 1 2 3
     * 4 5 6
     * c 7 8 9
     *
     * @param matrix matrix
     */
    public static void rotate(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a < c) {
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    public static void rotateEdge(int[][] m, int a, int b, int c, int d) {
        int tmp = 0;
        for (int i = 0; i < d - b; i++) {
            // 保存左上角
            tmp = m[a][b + i];
            // 左下角换到左上角
            m[a][b + i] = m[c - i][b];
            // 右下角换到左下角
            m[c - i][b] = m[c][d - i];
            // 右上角换到右下角
            m[c][d - i] = m[a + i][d];
            // 左上角换到右上角
            m[a + i][d] = tmp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}
