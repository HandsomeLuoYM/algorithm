package com.algorithm.two_zero_two_one.february.数组的度;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming
 * @date 2021/2/20 - 0:50
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
     *
     * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：[1, 2, 2, 3, 1]
     * 输出：2
     * 解释：
     * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
     * 连续子数组里面拥有相同度的有如下所示:
     * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
     * 最短连续子数组[2, 2]的长度为2，所以返回2.
     * 示例 2：
     *
     * 输入：[1,2,2,3,1,4,2]
     * 输出：6
     *  
     */
    /**
     * 思路：hash存储
     * 执行用时：32 ms, 在所有 Java 提交中击败了29.22%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了93.70%的用户
     */
    public int findShortestSubArray(int[] nums) {
        int length = nums.length;
        Map<Integer, int[]> index = new HashMap<>();
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (numMap.containsKey(nums[i])) {
                numMap.put(nums[i], numMap.get(nums[i]) + 1);
                index.put(nums[i], new int[]{index.get(nums[i])[0], i});
            }else {
                numMap.put(nums[i], 1);
                index.put(nums[i], new int[]{i, i});
            }
        }
        int max = numMap.get(nums[0]), rs = index.get(nums[0])[1] - index.get(nums[0])[0] + 1;
        for (Map.Entry<Integer, Integer> entry : numMap.entrySet()) {
            if (max < entry.getValue()) {
                max = entry.getValue();
                rs = index.get(entry.getKey())[1] - index.get(entry.getKey())[0] + 1;
            }else if (max == entry.getValue()) {
                rs = Math.min(rs, index.get(entry.getKey())[1] - index.get(entry.getKey())[0] + 1);
            }
        }
        return rs;
    }

    class Solution {
        public int findShortestSubArray(int[] nums) {
            Map<Integer, int[]> map = new HashMap<Integer, int[]>();
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (map.containsKey(nums[i])) {
                    map.get(nums[i])[0]++;
                    map.get(nums[i])[2] = i;
                } else {
                    map.put(nums[i], new int[]{1, i, i});
                }
            }
            int maxNum = 0, minLen = 0;
            for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
                int[] arr = entry.getValue();
                if (maxNum < arr[0]) {
                    maxNum = arr[0];
                    minLen = arr[2] - arr[1] + 1;
                } else if (maxNum == arr[0]) {
                    if (minLen > arr[2] - arr[1] + 1) {
                        minLen = arr[2] - arr[1] + 1;
                    }
                }
            }
            return minLen;
        }
    }

}
