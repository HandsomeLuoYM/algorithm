package com.algorithm.two_zero_two_zero.december.分发糖果;

/**
 * @author Ming
 * @date 2020/12/24 - 10:38
 * @describe
 */
public class LeetCode {
    /**
     * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     *
     * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
     *
     * 每个孩子至少分配到 1 个糖果。
     * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
     *
     * 输入: [1,0,2]
     * 输出: 5
     * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
     *
     * 输入: [1,2,2]
     * 输出: 4
     * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
     *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
     */
    /**
     * 两次遍历
     * 执行用时：3 ms, 在所有 Java 提交中击败了66.08%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了58.19%的用户
     */
    class Solution {
        public int candy(int[] ratings) {
            int n = ratings.length;
            int[] left = new int[n];
            for (int i = 0; i < n; i++) {
                if (i > 0 && ratings[i] > ratings[i - 1]) {
                    left[i] = left[i - 1] + 1;
                } else {
                    left[i] = 1;
                }
            }
            int right = 0, ret = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                    right++;
                } else {
                    right = 1;
                }
                ret += Math.max(left[i], right);
            }
            return ret;
        }
    }

    /**
     * 贪心
     */
    class Solution1 {
        public int candy(int[] ratings) {
            int n = ratings.length;
            int ret = 1;
            int inc = 1, dec = 0, pre = 1;
            for (int i = 1; i < n; i++) {
                if (ratings[i] >= ratings[i - 1]) {
                    dec = 0;
                    pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                    ret += pre;
                    inc = pre;
                } else {
                    dec++;
                    if (dec == inc) {
                        dec++;
                    }
                    ret += dec;
                    pre = 1;
                }
            }
            return ret;
        }
    }

}
