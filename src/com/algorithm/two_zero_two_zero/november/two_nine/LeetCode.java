package com.algorithm.two_zero_two_zero.november.two_nine;

import java.util.Arrays;

/**
 * @author Ming
 * @date 2020/11/29 - 2:22
 * @describe 976. 三角形的最大周长
 */
public class LeetCode {

    /**
     * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
     *
     * 如果不能形成任何面积不为零的三角形，返回 0。
     *
     * 输入：[1,2,1]
     * 输出：0
     *
     * 输入：[3,2,3,4]
     * 输出：10
     */

    /**
     * 自己的思路（和官方题解一样）:
     *      贪心+排序，先排序，再找最适合的
     * 执行用时：8 ms, 在所有 Java 提交中击败了97.45%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了95.54%的用户
     */
    public int largestPerimeter(int[] A) {
        if (A.length<3) return 0;
        Arrays.sort(A);
        int left = A.length-3;
        while (left>=0){
            if (A[left] + A[left+1] > A[left+2]) return A[left] + A[left+1] + A[left+2];
            left--;
        }
        return 0;
    }
}
