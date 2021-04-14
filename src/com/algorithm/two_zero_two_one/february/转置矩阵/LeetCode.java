package com.algorithm.two_zero_two_one.february.转置矩阵;

/**
 * @author Ming
 * @date 2021/2/25 - 1:36
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
     *
     * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
     *
     * 示例 1：
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[1,4,7],[2,5,8],[3,6,9]]
     * 示例 2：
     *
     * 输入：matrix = [[1,2,3],[4,5,6]]
     * 输出：[[1,4],[2,5],[3,6]]
     *
     */
    /**
     * 思路：直接逆转
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了27.49%的用户
     */
    public int[][] transpose(int[][] matrix) {
        int row = matrix.length, cell = matrix[0].length;
        int[][] rs = new int[cell][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cell; j++) {
                rs[j][i] = matrix[i][j];
            }
        }
        return rs;
    }
}
