package com.algorithm.two_zero_two_one.february.托普利茨矩阵;

/**
 * @author Ming
 * @date 2021/2/22 - 0:02
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
     *
     * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
     *
     * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
     * 输出：true
     * 解释：
     * 在上述矩阵中, 其对角线为:
     * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
     * 各条对角线上的所有元素均相同, 因此答案是 True 。
     *
     * 输入：matrix = [[1,2],[2,2]]
     * 输出：false
     * 解释：
     * 对角线 "[1, 2]" 上的元素不同。
     */
    /**
     * 思路：直接遍历
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了63.13%的用户
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int row = matrix.length, cell = matrix[0].length, param;
        for (int i = 0; i < cell; i++) {
            param = matrix[row - 1][i];
            for (int j = i; row - 1 - (i - j) >= 0 && j >= 0; j--) {
                if (matrix[row - 1 - (i - j)][j] != param)
                    return false;
            }
        }
        for (int i = 0; i < row; i++) {
            param = matrix[i][cell - 1];
            for (int j = i; cell - 1 - (i - j) >= 0 && j >= 0; j--) {
                if (matrix[j][cell - 1 - (i - j)] != param)
                    return false;
            }
        }
        return true;
    }

}
