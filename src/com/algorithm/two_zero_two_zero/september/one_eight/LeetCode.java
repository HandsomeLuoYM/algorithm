package com.algorithm.two_zero_two_zero.september.one_eight;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ming
 * @date 2020/9/18 - 10:03
 * @describe 47. 全排列 II
 */
public class LeetCode {
    /**
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     *
     * 输入: [1,1,2]
     * 输出:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
     */
    /**
     * 自己的解法：遍历添加修改
     * 执行用时：101 ms, 在所有 Java 提交中击败了9.11%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了23.82%的用户
     */
    Set<List<Integer>> all = new HashSet();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length==0) return new ArrayList<>();
        recursion(nums,0);
        return new ArrayList<>(all);
    }

    public void recursion(int[] nums,int now){
        if (now==nums.length-1) {
            all.add(Arrays.stream( nums ).boxed().collect(Collectors.toList()));
            return;
        }
        int length = nums.length;
        for (int i=now;i<length;i++){
            swag(nums,now,i);
            recursion(nums,now+1);
            swag(nums,now,i);
        }
    }

    public void swag(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    /**
     * 自己的做法二
     * 执行用时：30 ms, 在所有 Java 提交中击败了15.90%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了57.78%的用户
     */
    class leet{
        Set<List<Integer>> all = new HashSet();
        public List<List<Integer>> permuteUnique(int[] nums) {
            if (nums.length==0) return new ArrayList<>();
            recursion(nums,0,new ArrayList<>());
            return new ArrayList<>(all);
        }

        public void recursion(int[] nums,int now,List<Integer> list){
            if (now==nums.length-1) {
                list.add(nums[now]);
                all.add(new ArrayList<>(list));
                list.remove(now);
                return;
            }
            int length = nums.length;
            for (int i=now;i<length;i++){
                swag(nums,now,i);
                list.add(nums[now]);
                recursion(nums,now+1,list);
                list.remove(now);
                swag(nums,now,i);
            }
        }

        public void swag(int[] nums,int i,int j){
            int temp = nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
        }
    }

    /**
     * 官方题解
     * 思路：先排序，同时也设置一个数组，代表是否已经遍历过，再遍历时如果
     */
    class Solution {
        boolean[] vis;

        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            List<Integer> perm = new ArrayList<Integer>();
            vis = new boolean[nums.length];
            Arrays.sort(nums);
            backtrack(nums, ans, 0, perm);
            return ans;
        }

        public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
            //添加数组
            if (idx == nums.length) {
                ans.add(new ArrayList<Integer>(perm));
                return;
            }
            for (int i = 0; i < nums.length; ++i) {
                //避免重复
                if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                    continue;
                }
                perm.add(nums[i]);
                vis[i] = true;
                backtrack(nums, ans, idx + 1, perm);
                vis[i] = false;
                perm.remove(idx);
            }
        }
    }

}
