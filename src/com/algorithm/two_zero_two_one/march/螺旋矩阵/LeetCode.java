package com.algorithm.two_zero_two_one.march.螺旋矩阵;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ming
 * @date 2021/3/16 - 0:55
 * @describe
 */
public class LeetCode {

    /**
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     *
     * 示例 1：
     * 输入：n = 3
     * 输出：[[1,2,3],[8,9,4],[7,6,5]]
     *
     * 示例 2：
     * 输入：n = 1
     * 输出：[[1]]
     */
    /**
     * 思路：遍历
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 73.02% 的用户
     */
    public int[][] generateMatrix(int n) {
        int left = 0, right = n - 1, up = 0, down = n - 1, now = 1;
        int[][] rs = new int[n][n];
        while (left < right && up < down) {
            for (int i = left; i < right; i++) {
                rs[up][i] = now++;
            }
            for (int i = up; i < down; i++) {
                rs[i][right] = now++;
            }
            for (int i = right; i > left; i--) {
                rs[down][i] = now++;
            }
            for (int i = down; i > up; i--) {
                rs[i][left] = now++;
            }
            left++;
            right--;
            up++;
            down--;
        }
        if (n % 2 == 1) rs[n / 2][n / 2] = now++;
        return rs;
    }
}
