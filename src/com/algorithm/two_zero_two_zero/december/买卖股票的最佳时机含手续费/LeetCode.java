package com.algorithm.two_zero_two_zero.december.买卖股票的最佳时机含手续费;

/**
 * @author Ming
 * @date 2020/12/17 - 1:28
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
     *
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     *
     * 返回获得利润的最大值。
     *
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     *
     * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
     * 输出: 8
     * 解释: 能够达到的最大利润:
     * 在此处买入 prices[0] = 1
     * 在此处卖出 prices[3] = 8
     * 在此处买入 prices[4] = 4
     * 在此处卖出 prices[5] = 9
     * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
     *
     * 注意:
     * 0 < prices.length <= 50000.
     * 0 < prices[i] < 50000.
     * 0 <= fee < 50000.
     */
    /**
     * 思路：
     *      贪心，当卖出时，不要直接把最小值置为 Integer.MAX_VALUE，
     *      把他变成当前价格减 fee 就行。这样可以避免小山峰。
     * 执行用时：4 ms, 在所有 Java 提交中击败了99.77%的用户
     * 内存消耗：47.5 MB, 在所有 Java 提交中击败了82.99%的用户
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int res = 0, min = prices[0];
        for (int i = 1; i < len; i++) {
            if (prices[i] < min) {
                //未发生买卖时找到第一个最小数，如果发生过买卖则比较当前价格和上次卖出时的价格减去手续费
                min = prices[i];
            } else {
                if (prices[i] - fee > min) {
                    res += prices[i] - min - fee;
                    //当前的价格已经减过手续费，所以min的值应为当前价格减去手续费。
                    min = prices[i] - fee;
                }
            }
        }
        return res;
    }

    /**
     * 动态规划
     * 定义状态 dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润，
     * dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润（i 从 0 开始）。
     *
     * 转移方程：
     *  dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]−fee}
     *  dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]}
     */
    class Solution {
        public int maxProfit(int[] prices, int fee) {
            int n = prices.length;
            int[][] dp = new int[n][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            for (int i = 1; i < n; ++i) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);//没有股票
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);//有股票
            }
            return dp[n - 1][0];
        }
    }
}
