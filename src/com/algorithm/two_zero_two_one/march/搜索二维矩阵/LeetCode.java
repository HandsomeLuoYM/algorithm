package com.algorithm.two_zero_two_one.march.搜索二维矩阵;

/**
 * @author Ming
 * @date 2021/3/30 - 0:52
 * @describe
 */
public class LeetCode {
    /**
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     *
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     *  
     *
     * 示例 1：
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * 输出：true
     *
     * 示例 2：
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
     * 输出：false
     *
     */
    /**
     * 思路：二分查找
     * 行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了87.69%的用户
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowIndex = binarySearchFirstColumn(matrix, target);
        if (rowIndex < 0) {
            return false;
        }
        return binarySearchRow(matrix[rowIndex], target);
    }

    public int binarySearchFirstColumn(int[][] matrix, int target) {
        int low = -1, high = matrix.length - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (matrix[mid][0] <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean binarySearchRow(int[] row, int target) {
        int low = 0, high = row.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    /**
     * 一次二分：变成一个数组再查
     */
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            int low = 0, high = m * n - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                int x = matrix[mid / n][mid % n];
                if (x < target) {
                    low = mid + 1;
                } else if (x > target) {
                    high = mid - 1;
                } else {
                    return true;
                }
            }
            return false;
        }
    }

}
