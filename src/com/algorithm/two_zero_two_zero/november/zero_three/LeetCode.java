package com.algorithm.two_zero_two_zero.november.zero_three;

/**
 * @author Ming
 * @date 2020/11/3 - 0:03
 * @describe 941. 有效的山脉数组
 */
public class LeetCode {
    /**
     * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
     *
     * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
     *
     * A.length >= 3
     * 在 0 < i < A.length - 1 条件下，存在 i 使得：
     * A[0] < A[1] < ... A[i-1] < A[i]
     * A[i] > A[i+1] > ... > A[A.length - 1]
     *
     */
    /**
     * 自己的思路：
     *      先排除一些意外情况，然后再设置一个flag标记上坡还是下坡，边判断边比较
     * 执行用时：2 ms, 在所有 Java 提交中击败了41.53%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了68.56%的用户
     */
    public boolean validMountainArray(int[] A) {
        int length = A.length;
        if (length<3) return false;
        int i = 1;
        boolean up = true;
        for (; i<length; i++){
            if (up && A[i]>A[i-1]){
                continue;
            }else if (A[i] == A[i-1]){
                return false;
            }
            up = false;
            if ( A[i]>A[i-1]) return false;
        }
        return up == false && i == length;
    }

    /**
     * 官方题解，两边往中间遍历
     */
    class Solution {
        public boolean validMountainArray(int[] A) {
            int N = A.length;
            int i = 0;

            // 递增扫描
            while (i + 1 < N && A[i] < A[i + 1]) {
                i++;
            }

            // 最高点不能是数组的第一个位置或最后一个位置
            if (i == 0 || i == N - 1) {
                return false;
            }

            // 递减扫描
            while (i + 1 < N && A[i] > A[i + 1]) {
                i++;
            }

            return i == N - 1;
        }
    }

}
