package com.algorithm.two_zero_two_zero.october.two_zero;

/**
 * @author Ming
 * @date 2020/10/20 - 14:45
 * @describe 剑指 Offer 63. 股票的最大利润
 */
public class Dp01 {

    /**
     * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
     *
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     *
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */

    /**
     * 自己的思路：
     *      设置一个最大和最小
     *              当当前值大于最大值则替换最大值，并且判断结果
     *              当当前值小于最小值则替换最小值和最大值
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.98%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了99.79%的用户
     */
    public int maxProfit(int[] prices) {
        int length = prices.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, result=Integer.MIN_VALUE;
        for (int i =0; i<length; i++){
            if (max<prices[i]) {
                max = prices[i];
                if (result<max - min) result = max - min;
            }
            if (min>prices[i]){
                min = prices[i];
                max = Integer.MIN_VALUE;
            }
        }
        return Math.max(0,result);
    }

}
