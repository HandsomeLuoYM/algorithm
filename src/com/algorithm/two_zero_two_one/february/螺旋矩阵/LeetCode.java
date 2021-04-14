package com.algorithm.two_zero_two_one.february.螺旋矩阵;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ming
 * @date 2021/2/9 - 11:23
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     *
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     */
    /**
     * 模拟
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了20.57%的用户
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0, right = matrix[0].length - 1, up = 0, down = matrix.length - 1;
        List<Integer> rs = new LinkedList<>();
        //大体部分
        while (left < right && up < down) {
            for (int i = left; i < right; i++) {
                rs.add(matrix[up][i]);
            }
            for (int i = up; i < down; i++) {
                rs.add(matrix[i][right]);
            }
            for (int i = right; i > left; i--) {
                rs.add(matrix[down][i]);
            }
            for (int i = down; i > up; i--) {
                rs.add(matrix[i][left]);
            }
            left++;
            right--;
            up++;
            down--;
        }
        //收尾调整
        if (left == right || up == down) {
            if (left == right && up < down) {
                for (int i = up; i <= down; i++) {
                    rs.add(matrix[i][right]);
                }
            }
            if (left< right && up == down) {
                for (int i = left; i <= right; i++) {
                    rs.add(matrix[up][i]);
                }
            }
            if (left == right && up == down) {
                rs.add(matrix[up][right]);
            }
        }
        return rs;
    }
}
