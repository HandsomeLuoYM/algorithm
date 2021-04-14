package com.algorithm.two_zero_two_one.march.子集II;

import java.util.*;

/**
 * @author Ming
 * @date 2021/3/31 - 0:17
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     * 示例 2：
     *
     * 输入：nums = [0]
     * 输出：[[],[0]]
     *
     */
    /**
     * 思路：
     *      暴力
     * 执行用时：3 ms, 在所有 Java 提交中击败了24.09%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了25.39%的用户
     */
    Set<List<Integer>> set = new HashSet<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>());
        return new ArrayList<>(set);
    }
    private void dfs(int[] nums, int index, List<Integer> list) {
        if (index == nums.length) set.add(list);
        else {
            ArrayList<Integer> newList = new ArrayList<>(list);
            newList.add(nums[index]);
            dfs(nums, index + 1, newList);
            dfs(nums, index + 1, list);
        }
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        l.subsetsWithDup(new int[]{1, 2, 2});
    }
}
