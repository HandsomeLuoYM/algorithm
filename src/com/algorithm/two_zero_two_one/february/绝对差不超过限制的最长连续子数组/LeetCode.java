package com.algorithm.two_zero_two_one.february.绝对差不超过限制的最长连续子数组;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * @author Ming
 * @date 2021/2/21 - 0:41
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
     *
     * 如果不存在满足条件的子数组，则返回 0 。
     *
     * 示例 1：
     *
     * 输入：nums = [8,2,4,7], limit = 4
     * 输出：2
     * 解释：所有子数组如下：
     * [8] 最大绝对差 |8-8| = 0 <= 4.
     * [8,2] 最大绝对差 |8-2| = 6 > 4.
     * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
     * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
     * [2] 最大绝对差 |2-2| = 0 <= 4.
     * [2,4] 最大绝对差 |2-4| = 2 <= 4.
     * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
     * [4] 最大绝对差 |4-4| = 0 <= 4.
     * [4,7] 最大绝对差 |4-7| = 3 <= 4.
     * [7] 最大绝对差 |7-7| = 0 <= 4.
     * 因此，满足题意的最长子数组的长度为 2 。
     * 示例 2：
     *
     * 输入：nums = [10,1,2,4,7,2], limit = 5
     * 输出：4
     * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
     * 示例 3：
     *
     * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
     * 输出：3
     */
    /**
     * 思路：滑动窗口，每次
     * 执行用时：44 ms, 在所有 Java 提交中击败了53.10%的用户
     * 内存消耗：46.9 MB, 在所有 Java 提交中击败了95.34%的用户
     */
    public int longestSubarray(int[] nums, int limit) {
        int length = nums.length, start = 0, minIndex = 0, maxIndex = 0, rs = 0, index;
        for (int i = 0; i < length; i++) {
            if (nums[i] <= nums[minIndex]) {
                if (nums[i] == nums[minIndex]) minIndex = i;
                else {
                    if (nums[maxIndex] - nums[i] <= limit) minIndex = i;
                    else {//需要更新最大值
                        minIndex = i;
                        //需要遍历查找适合的最大值
                        index = i;
                        for (int j = i; j > maxIndex; j--) {
                            // j 不满足，直接结束
                            if (nums[j] - nums[i] > limit) {
                                break;
                            }
                            if (nums[j] > nums[index]) {
                                index = j;
                            }
                            start = j;
                        }
                        maxIndex = index;
                    }
                }

            }else if (nums[i] >= nums[maxIndex]) {
                if (nums[i] == nums[maxIndex]) maxIndex = i;
                else {
                    if (nums[i] - nums[minIndex] <= limit) maxIndex = i;
                    else {//需要更新最小值
                        maxIndex = i;
                        //需要遍历查找适合的最小值
                        index = i;
                        for (int j = i; j > minIndex; j--) {
                            // j 不满足，直接结束
                            if (nums[i] - nums[j] > limit) {
                                break;
                            }
                            if (nums[j] < nums[index]) {
                                index = j;
                            }
                            start = j;
                        }
                        minIndex = index;
                    }
                }
            }
            rs = Math.max(rs, i - start + 1);
        }
        return rs;
    }

    class Solution {
        /**
         * 滑动窗口 + 有序集合
         * 我们可以枚举每一个位置作为右端点，找到其对应的最靠左的左端点，满足区间中最大值与最小值的差不超过 \textit{limit}limit。
         */
        public int longestSubarray(int[] nums, int limit) {
            TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
            int n = nums.length;
            int left = 0, right = 0;
            int ret = 0;
            while (right < n) {
                map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
                while (map.lastKey() - map.firstKey() > limit) {
                    map.put(nums[left], map.get(nums[left]) - 1);
                    if (map.get(nums[left]) == 0) {
                        map.remove(nums[left]);
                    }
                    left++;
                }
                ret = Math.max(ret, right - left + 1);
                right++;
            }
            return ret;
        }
    }
    /**
     * 滑动窗口 + 单调队列
     */
    class Solution1 {
        public int longestSubarray(int[] nums, int limit) {
            Deque<Integer> queMax = new LinkedList<Integer>();
            Deque<Integer> queMin = new LinkedList<Integer>();
            int n = nums.length;
            int left = 0, right = 0;
            int ret = 0;
            while (right < n) {
                while (!queMax.isEmpty() && queMax.peekLast() < nums[right]) {
                    queMax.pollLast();
                }
                while (!queMin.isEmpty() && queMin.peekLast() > nums[right]) {
                    queMin.pollLast();
                }
                queMax.offerLast(nums[right]);
                queMin.offerLast(nums[right]);
                while (!queMax.isEmpty() && !queMin.isEmpty() && queMax.peekFirst() - queMin.peekFirst() > limit) {
                    if (nums[left] == queMin.peekFirst()) {
                        queMin.pollFirst();
                    }
                    if (nums[left] == queMax.peekFirst()) {
                        queMax.pollFirst();
                    }
                    left++;
                }
                ret = Math.max(ret, right - left + 1);
                right++;
            }
            return ret;
        }
    }

}
