package com.algorithm.two_zero_two_zero.november.zero_serven;

/**
 * @author Ming
 * @date 2020/11/7 - 10:50
 * @describe 327. 区间和的个数
 */
public class LeetCode {
    /**
     * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
     * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
     *
     * 说明:
     * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
     *
     * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
     * 输出: 3
     * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。[i,j]表示从nums第i个到第j个的和
     *
     */
    /**
     * 自己的做法：
     *      暴力解法，求出从 i 到 j 的所有情况。如何判断是否要添加
     * 执行用时：181 ms, 在所有 Java 提交中击败了18.99%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了98.38%的用户
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        int length = nums.length , number = 0;
        for (int i = 0; i<length; i++){
            long sum = 0;
            for (int j = i; j<length; j++){
                sum += nums[j];
                if (sum <= upper && sum >= lower)
                    number++;
            }
        }
        return number;
    }

    /**
     * 官方题解一：
     *      归并思想
     */
    class Solution {
        public int countRangeSum(int[] nums, int lower, int upper) {
            long s = 0;
            long[] sum = new long[nums.length + 1];
            //求出从 0 到 i 的数和
            for (int i = 0; i < nums.length; ++i) {
                s += nums[i];
                sum[i + 1] = s;
            }
            return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
        }

        public int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
            if (left == right) {
                return 0;
            } else {
                int mid = (left + right) / 2;
                int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
                int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
                int ret = n1 + n2; // 求出在每一段中的所有情况，在添加最后两段和的所有情况

                // 首先统计下标对的数量
                int i = left;
                int l = mid + 1;
                int r = mid + 1;
                while (i <= mid) {
                    while (l <= right && sum[l] - sum[i] < lower) {
                        l++;
                    }
                    while (r <= right && sum[r] - sum[i] <= upper) {
                        r++;
                    }
                    ret += r - l;
                    i++;
                }

                // 随后合并两个排序数组
                int[] sorted = new int[right - left + 1];
                int p1 = left, p2 = mid + 1;
                int p = 0;
                while (p1 <= mid || p2 <= right) {
                    if (p1 > mid) {
                        sorted[p++] = (int) sum[p2++];
                    } else if (p2 > right) {
                        sorted[p++] = (int) sum[p1++];
                    } else {
                        if (sum[p1] < sum[p2]) {
                            sorted[p++] = (int) sum[p1++];
                        } else {
                            sorted[p++] = (int) sum[p2++];
                        }
                    }
                }
                for (int j = 0; j < sorted.length; j++) {
                    sum[left + j] = sorted[j];
                }
                return ret;

            }
        }
    }

}
