package com.algorithm.two_zero_two_one.january.斐波那契数;

/**
 * @author Ming
 * @date 2021/1/4 - 10:30
 * @describe
 */
public class LeetCode {
    /**
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     *
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     */
    /**
     * 自己的思路：
     *      迭代，空间换时间
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35 MB, 在所有 Java 提交中击败了88.41%的用户
     */
    public int fib(int n) {
        if (n == 0 || n == 1) return n == 0 ? 0 : 1;
        int[] number = new int[n+1];
        number[0] = 0;
        number[1] = 1;
        for (int i = 2; i <= n; i++){
            number[i] = number[i-1] + number[i-2];
        }
        return number[n];
    }

    /**
     * 动态规划
     */
    class Solution {
        public int fib(int n) {
            if (n < 2) {
                return n;
            }
            int p = 0, q = 0, r = 1;
            for (int i = 2; i <= n; ++i) {
                p = q;
                q = r;
                r = p + q;
            }
            return r;
        }
    }

}
