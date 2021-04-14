package com.algorithm.two_zero_two_one.march.矩阵置零;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ming
 * @date 2021/3/21 - 0:17
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * 输出:
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     * 示例 2:
     *
     * 输入:
     * [
     *   [0,1,2,0],
     *   [3,4,5,2],
     *   [1,3,1,5]
     * ]
     * 输出:
     * [
     *   [0,0,0,0],
     *   [0,4,5,0],
     *   [0,3,1,0]
     * ]
     */
    /**
     * 思路：先找到再修改
     * 执行用时：2 ms, 在所有 Java 提交中击败了40.58%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了79.40%的用户
     */
    public void setZeroes(int[][] matrix) {
        List<int[]> list = new LinkedList<int[]>();
        int row = matrix.length, cell = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cell; j++) {
                if (matrix[i][j] == 0) list.add(new int[]{i, j});
            }
        }
        change(matrix, list);
    }

    private void change(int[][] matrix, List<int[]> list) {
        int row = matrix.length, cell = matrix[0].length;
        for (int[] ints : list) {
            for (int i = 0; i < cell; i++) {
                matrix[ints[0]][i] = 0;
            }
            for (int i = 0; i < row; i++) {
                matrix[i][ints[1]] = 0;
            }
        }
    }
}
