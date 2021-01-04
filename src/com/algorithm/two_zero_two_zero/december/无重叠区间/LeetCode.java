package com.algorithm.two_zero_two_zero.december.无重叠区间;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Ming
 * @date 2020/12/31 - 22:34
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     *
     * 注意:
     *
     * 可以认为区间的终点总是大于它的起点。
     * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     *
     * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
     * 输出: 1
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     */
    /**
     * 执行用时：140 ms, 在所有 Java 提交中击败了5.21%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了73.84%的用户
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

        int n = intervals.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (intervals[j][1] <= intervals[i][0]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        return n - Arrays.stream(f).max().getAsInt();
    }

    /**
     * 和我的思路一样：
     *      先将第二个数进行预排序，然后再找到最小满足的，贪心思想
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了83.29%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了55.06%的用户
     */
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length == 0) {
                return 0;
            }

            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] interval1, int[] interval2) {
                    return interval1[1] - interval2[1];
                }
            });

            int n = intervals.length;
            int right = intervals[0][1];
            int ans = 1;
            for (int i = 1; i < n; ++i) {
                if (intervals[i][0] >= right) {
                    ++ans;
                    right = intervals[i][1];
                }
            }
            return n - ans;
        }
    }

}
