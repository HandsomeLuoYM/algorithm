package com.algorithm.two_zero_two_zero.december.摆动序列;

/**
 * @author Ming
 * @date 2020/12/12 - 0:38
 * @describe
 */
public class LeetCode {

    /**
     * 果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。
     * 少于两个元素的序列也是摆动序列。
     *
     * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
     * 相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     *
     * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。
     * 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
     *
     */
    /**
     * 自己的思路（贪心）：找到峰
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了46.04%的用户
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int prevdiff = nums[1] - nums[0];
        int ret = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                ret++;
                prevdiff = diff;
            }
        }
        return ret;
    }

    /**
     * 自己的做法：
     *      对于边界和目前位置定位不准确
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了85.28%的用户
     */
    public int wiggleMaxLength1(int[] nums) {
        int all = 1, length = nums.length, j, i=1;
        if (length<=1) return length;
        boolean flag = true;
        while (i<length && nums[i-1]==nums[i]) i++;
        if (i<length && nums[i-1]>nums[i]) flag = false;
        while (i<length){
            if (flag){
                while (i<length && nums[i]>=nums[i-1]) i++;
                all++;
                flag = false;
            }else {
                while (i<length && nums[i]<=nums[i-1]) i++;
                all++;
                flag = true;
            }
        }
        return all;
    }

    /**
     * 动态规划
     */
    class Solution {
        public int wiggleMaxLength(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return n;
            }
            int[] up = new int[n];
            int[] down = new int[n];
            up[0] = down[0] = 1;
            for (int i = 1; i < n; i++) {
                if (nums[i] > nums[i - 1]) {
                    up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                    down[i] = down[i - 1];
                } else if (nums[i] < nums[i - 1]) {
                    up[i] = up[i - 1];
                    down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
                } else {
                    up[i] = up[i - 1];
                    down[i] = down[i - 1];
                }
            }
            return Math.max(up[n - 1], down[n - 1]);
        }
    }


    /**
     * 动态规划二
     */
    class Solution1 {
        public int wiggleMaxLength(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return n;
            }
            int up = 1, down = 1;
            for (int i = 1; i < n; i++) {
                if (nums[i] > nums[i - 1]) {
                    up = Math.max(up, down + 1);
                } else if (nums[i] < nums[i - 1]) {
                    down = Math.max(up + 1, down);
                }
            }
            return Math.max(up, down);
        }
    }

}
