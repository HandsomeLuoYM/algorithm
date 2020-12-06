package com.algorithm.december.拼接最大数;

import java.util.Arrays;

/**
 * @author Ming
 * @date 2020/12/2 - 9:25
 * @describe 321. 拼接最大数
 */
public class LeetCode {

    /**
     * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
     * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
     *
     * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
     *
     * 说明: 请尽可能地优化你算法的时间和空间复杂度。
     */

    /**
     * 自己的思路：
     *      和题解差不多，但是未考虑到两个数组的的长度情况，可以穷举两个长度的情况再进行比较
     *
     *  执行用时：8 ms, 在所有 Java 提交中击败了97.00%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了53.50%的用户
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n), end = Math.min(k, m);
        for (int i = start; i <= end; i++) {
            //获取最大的子串
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            //融合拼接串
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            //比较是否要替换
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    /**
     * 找到最大的 k 子串
     * 我的思路：找到第 0 -- length-k 中最大，然后作为第一位 a1，再从 a1 -- length-k+1 中找到最大 a2，以此类推...
     */
    public int[] maxSubsequence(int[] nums, int k) {
        int[] subNums = new int[k];
        int cur = 0, rem = nums.length - k; // rem 表示还可以删去多少字符
        for (int i = 0; i < nums.length; i++) {
            while (cur > 0 && subNums[cur - 1] < nums[i] && rem > 0) {
                cur--;
                rem--;
            }
            if (cur < k) {
                subNums[cur++] = nums[i];
            } else {
                rem--; // 避免超过边界而少删字符
            }
        }
        return subNums;
    }

    public int[] merge(int[] subsequence1, int[] subsequence2) {
        //谁大谁先
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }


}
