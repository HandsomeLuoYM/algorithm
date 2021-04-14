package com.algorithm.two_zero_two_one.january.买卖股票的最佳时机;

/**
 * @author Ming
 * @date 2021/1/31 - 11:29
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     *
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    /**
     * 思路：贪心
     * 执行用时：3 ms, 在所有 Java 提交中击败了35.47%的用户
     * 内存消耗：51.2 MB, 在所有 Java 提交中击败了12.87%的用户
     */
    public int maxProfit(int[] prices) {
        int length = prices.length, min = prices[0], rs = Integer.MIN_VALUE;
        for (int i = 1; i < length; i++) {
            rs = Math.max(rs, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return Math.max(rs, 0);
    }
}
