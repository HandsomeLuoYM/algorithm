package com.algorithm.two_zero_two_one.march.二维区域和检索_矩阵不可变;

/**
 * @author Ming
 * @date 2021/3/2 - 23:57
 * @describe
 */
public class NumMatrix {
    /**
     * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
     *
     *
     * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
     *
     * 示例：
     *
     * 给定 matrix = [
     *   [3, 0, 1, 4, 2],
     *   [5, 6, 3, 2, 1],
     *   [1, 2, 0, 1, 5],
     *   [4, 1, 0, 1, 7],
     *   [1, 0, 3, 0, 5]
     * ]
     *
     * sumRegion(2, 1, 4, 3) -> 8
     * sumRegion(1, 1, 2, 2) -> 11
     * sumRegion(1, 2, 2, 4) -> 12
     *
     */
    /**
     * 思路：前缀和 + 前前缀和
     * 执行用时：13 ms, 在所有 Java 提交中击败了99.88%的用户
     * 内存消耗：44.2 MB, 在所有 Java 提交中击败了31.07%的用户
     */
    int[][] index;
    public NumMatrix(int[][] matrix) {
        if (matrix == null) {
            index = new int[1][1];
            return;
        }
        int row = matrix.length, cell = matrix[0].length, all = 0;
        index = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cell; j++) {
                all += matrix[i][j];
                index[i + 1][j + 1] = all + index[i][j + 1];
            }
            all = 0;
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return index[row2 + 1][col2 + 1] + index[row1][col1] - index[row2 + 1][col1] - index[row1][col2 + 1];
    }
}
