package com.algorithm.two_zero_two_one.february.爱生气的书店老板;

/**
 * @author Ming
 * @date 2021/2/23 - 1:28
 * @describe
 */
public class LeetCode {
    /**
     * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
     *
     * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
     *
     * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
     *
     * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
     *  
     *
     * 示例：
     *
     * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
     * 输出：16
     * 解释：
     * 书店老板在最后 3 分钟保持冷静。
     * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
     *
     */
    /**
     * 思路：两次遍历 + 滑动窗口
     * 执行用时：3 ms, 在所有 Java 提交中击败了79.44%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了11.70%的用户
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int all = 0, length = customers.length, rs;
        for (int i = 0; i < length; i++) {
            if (grumpy[i] == 0) all += customers[i];
        }
        for (int i = 0; i < X; i++) {
            if (grumpy[i] == 1) all += customers[i];
        }
        rs = all;
        for (int i = X; i < length; i++) {
            if (grumpy[i] == 1) all += customers[i];
            if (grumpy[i - X] == 1) all -= customers[i - X];
            rs = Math.max(rs, all);
        }
        return rs;
    }
}
