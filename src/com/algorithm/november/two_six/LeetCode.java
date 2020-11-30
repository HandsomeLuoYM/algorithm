package com.algorithm.november.two_six;

import java.util.Arrays;

/**
 * @author Ming
 * @date 2020/11/26 - 15:58
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
     * 如果数组元素个数小于 2，则返回 0。
     *
     * 输入: [3,6,9,1]
     * 输出: 3
     * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
     *
     * 输入: [10]
     * 输出: 0
     * 解释: 数组元素个数小于 2，因此返回 0。
     *
     *      你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
     *      请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
     */

    /**
     * 基数排序
     * 执行用时：4 ms, 在所有 Java 提交中击败了41.08%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了79.91%的用户
     */
    class Solution {
        public int maximumGap(int[] nums) {
            //数组长度
            int n = nums.length;
            //排除掉异常情况
            if (n < 2) {
                return 0;
            }
            long exp = 1;
            int[] buf = new int[n];
            //找到最大值
            int maxVal = Arrays.stream(nums).max().getAsInt();

            while (maxVal >= exp) {
                //初始化另一个空间
                int[] cnt = new int[10];
                //计算个位情况
                for (int i = 0; i < n; i++) {
                    int digit = (nums[i] / (int) exp) % 10;
                    cnt[digit]++;
                }
                //计算索引位置
                for (int i = 1; i < 10; i++) {
                    cnt[i] += cnt[i - 1];
                }
                for (int i = n - 1; i >= 0; i--) {
                    int digit = (nums[i] / (int) exp) % 10;
                    //排序并且修改索引位置
                    buf[cnt[digit] - 1] = nums[i];
                    cnt[digit]--;
                }
                //修改数组空间，使其可以复用
                int[] temp = buf;
                buf = nums;
                nums = temp;
                exp *= 10;
            }

            int ret = 0;
            //找到最大相差的
            for (int i = 1; i < n; i++) {
                ret = Math.max(ret, nums[i] - nums[i - 1]);
            }
            return ret;
        }
    }

    class Solution1 {
        public int maximumGap(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return 0;
            }
            int minVal = Arrays.stream(nums).min().getAsInt();
            int maxVal = Arrays.stream(nums).max().getAsInt();
            int d = Math.max(1, (maxVal - minVal) / (n - 1));
            int bucketSize = (maxVal - minVal) / d + 1;

            int[][] bucket = new int[bucketSize][2];
            for (int i = 0; i < bucketSize; ++i) {
                Arrays.fill(bucket[i], -1); // 存储 (桶内最小值，桶内最大值) 对， (-1, -1) 表示该桶是空的
            }
            for (int i = 0; i < n; i++) {
                //计算放在哪个桶
                int idx = (nums[i] - minVal) / d;
                if (bucket[idx][0] == -1) {
                    bucket[idx][0] = bucket[idx][1] = nums[i];
                } else {
                    //修改桶内最小值和最大值
                    bucket[idx][0] = Math.min(bucket[idx][0], nums[i]);
                    bucket[idx][1] = Math.max(bucket[idx][1], nums[i]);
                }
            }
            //只需要修改一次桶就行
            int ret = 0;
            int prev = -1;
            //计算最大值
            for (int i = 0; i < bucketSize; i++) {
                if (bucket[i][0] == -1) {
                    continue;
                }
                if (prev != -1) {
                    ret = Math.max(ret, bucket[i][0] - bucket[prev][1]);
                }
                prev = i;
            }
            return ret;
        }
    }


}
