package com.algorithm.two_zero_two_one.february.数组拆分I;

import java.util.Arrays;

/**
 * @author Ming
 * @date 2021/2/16 - 1:20
 * @describe
 */
public class LeetCode {

    /**
     * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
     *
     * 返回该 最大总和 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,4,3,2]
     * 输出：4
     * 解释：所有可能的分法（忽略元素顺序）为：
     * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
     * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
     * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
     * 所以最大总和为 4
     * 示例 2：
     *
     * 输入：nums = [6,2,6,5,1,2]
     * 输出：9
     * 解释：最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9
     *
     */
    /**
     * 思路：先排序，后只需要将索引为单数的相加即可
     * 执行用时：13 ms, 在所有 Java 提交中击败了96.18%的用户
     * 内存消耗：40.4 MB, 在所有 Java 提交中击败了78.98%的用户
     */
    public int arrayPairSum(int[] nums) {
        int length =  nums.length / 2, rs = 0;
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            rs += nums[i * 2];
        }
        return rs;
    }

}
