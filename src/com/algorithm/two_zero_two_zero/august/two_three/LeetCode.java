package com.algorithm.two_zero_two_zero.august.two_three;

/**
 * @author Ming
 * @date 2020/8/23 - 14:19
 * @describe  201
 */
public class LeetCode {

    /**
     * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
     */

    /**
     * 自己的想法
     *
     * 我们的想法是将两个数字不断向右移动，直到数字相等，即数字被缩减为它们的公共前缀。
     * 然后，通过将公共前缀向左移动，将零添加到公共前缀的右边以获得最终结果。
     *
     * 执行用时：7 ms, 在所有 Java 提交中击败了39.69%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了71.95%的用户
     */
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;//除以2
            n >>= 1;//除以2
            ++shift;
        }
        return m << shift;//乘以 2的shift次方
    }

    /**
     * 还有一个位移相关的算法叫做「Brian Kernighan 算法」，它用于清除二进制串中最右边的 11。
     *
     * Brian Kernighan 算法的关键在于我们每次对 number 和 number−1 之间进行按位与运算后，number 中最右边的 11 会被抹去变成 00。
     *
     * 基于上述技巧，我们可以用它来计算两个二进制字符串的公共前缀。
     *
     * 其思想是，对于给定的范围 [m,n]（m<n），我们可以对数字 n 迭代地应用上述技巧，清除最右边的 1，直到它小于或等于 m，
     * 此时非公共前缀部分的 1 均被消去。因此最后我们返回 n 即可。
     *
     */
    class Solution {
        public int rangeBitwiseAnd(int m, int n) {
            while (m < n) {
                // 抹去最右边的 1
                n = n & (n - 1);
            }
            return n;
        }
    }

}
