package com.algorithm.two_zero_two_one.April.丑数;

/**
 * @author Ming
 * @date 2021/4/10 - 0:43
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
     *
     * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
     *
     * 示例 1：
     *
     * 输入：n = 6
     * 输出：true
     * 解释：6 = 2 × 3
     * 示例 2：
     *
     * 输入：n = 8
     * 输出：true
     * 解释：8 = 2 × 2 × 2
     * 示例 3：
     *
     * 输入：n = 14
     * 输出：false
     * 解释：14 不是丑数，因为它包含了另外一个质因数 7 。
     * 示例 4：
     *
     * 输入：n = 1
     * 输出：true
     * 解释：1 通常被视为丑数。
     *
     */
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了41.71%的用户
     */
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}
