package com.algorithm.two_zero_two_one.April.接雨水;

/**
 * @author Ming
 * @date 2021/3/30 - 22:48
 * @describe
 */
public class LeetCode {
    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * 示例 1：
     *
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * 示例 2：
     *
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     *  
     */
    public int trap(int[] height) {
        int length = height.length, now = 1, start, rs = 0;
        while (now < length) {
            if (height[now] < height[now - 1]) {

            }
        }
        return rs;
    }
}
