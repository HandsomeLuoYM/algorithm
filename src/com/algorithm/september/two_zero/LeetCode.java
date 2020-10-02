package com.algorithm.september.two_zero;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/20 - 10:54
 * @describe 78子集
 */
public class LeetCode {
    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */

    /**
     * 自己的做法
     * 思路：遍历每一个元素，然后分为要和不要
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.39%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了14.06%的用户
     */
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length==0) return lists;
        add(nums,0,new ArrayList<Integer>());
        return lists;
    }

    public void add(int[] nums,int index,List<Integer> list){
        if (index==nums.length){
            lists.add(list);
            return;
        }else {
            add(nums, index+1, list);//不添加
            List<Integer> newList = new ArrayList<>(list);
            newList.add(nums[index]);
            add(nums, index+1, newList);//添加
        }
    }

    /**
     * 官方题解
     */
    class Solution {
        List<Integer> t = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        public List<List<Integer>> subsets(int[] nums) {
            int n = nums.length;
            for (int mask = 0; mask < (1 << n); ++mask) {
                t.clear();
                for (int i = 0; i < n; ++i) {
                    if ((mask & (1 << i)) != 0) {
                        t.add(nums[i]);
                    }
                }
                ans.add(new ArrayList<Integer>(t));
            }
            return ans;
        }
    }


}
