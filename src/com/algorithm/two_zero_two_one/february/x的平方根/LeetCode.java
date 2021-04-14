package com.algorithm.two_zero_two_one.february.x的平方根;

/**
 * @author Ming
 * @date 2021/2/7 - 1:40
 * @describe
 */
public class LeetCode {
    /**
     * 实现 int sqrt(int x) 函数。
     *
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 输入: 4
     * 输出: 2
     *
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     */
    /**
     * 思路：API调用
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.7 MB, 在所有 Java 提交中击败了14.01%的用户
     */
    public int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }

    /**
     * 方法二：二分查找
     */
    class Solution {
        public int mySqrt(int x) {
            int l = 0, r = x, ans = -1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if ((long) mid * mid <= x) {
                    ans = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return ans;
        }
    }

}
