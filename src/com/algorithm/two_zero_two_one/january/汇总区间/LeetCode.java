package com.algorithm.two_zero_two_one.january.汇总区间;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2021/1/10 - 1:00
 * @describe 228
 */
public class LeetCode {
    /**
     * 给定一个无重复元素的有序整数数组 nums 。
     *
     * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
     *
     * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
     *
     * "a->b" ，如果 a != b
     * "a" ，如果 a == b
     *
     * 输入：nums = [0,1,2,4,5,7]
     * 输出：["0->2","4->5","7"]
     * 解释：区间范围是：
     * [0,2] --> "0->2"
     * [4,5] --> "4->5"
     * [7,7] --> "7"
     *
     */
    /**
     * 自己的思路：
     *      边遍历，边找边界值
     * 执行用时：8 ms, 在所有 Java 提交中击败了70.20%的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了70.07%的用户
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> rs = new ArrayList<>();
        if (nums.length == 0) return rs;
        int length = nums.length, now = nums[0];
        String s;
        for (int i = 0; i < length; i++) {
            s = String.valueOf(nums[i]);
            now = nums[i];
            if (i + 1 < length && nums[i+1] == now+1) {
                while (i < length && nums[i] == now){
                    i++;
                    now++;
                }
                s += "->" + String.valueOf(nums[--i]);
            }
            rs.add(new String(s));
        }
        return rs;
    }

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        System.out.println(leetCode.summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
    }
}
