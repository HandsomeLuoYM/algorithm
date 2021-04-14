package com.algorithm.two_zero_two_one.january.三数之和;

import java.util.*;

/**
 * @author Ming
 * @date 2021/1/30 - 18:41
 * @describe
 */
public class LeetCode {

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     * 输入：nums = []
     * 输出：[]
     *
     * 输入：nums = [0]
     * 输出：[]
     */
    /**
     * 思路：排序加指针
     * 执行用时：29 ms, 在所有 Java 提交中击败了30.93%的用户
     * 内存消耗：42.3 MB, 在所有 Java 提交中击败了72.31%的用户
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) return new LinkedList<>();
        Arrays.sort(nums);
        List<List<Integer>> rs = new ArrayList<>();
        int length = nums.length;
        for (int i = 0; i <= length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] > 0) break;
            int j = i + 1, k = length-1;
            while (j < k) {
                if (j > i +1 && nums[j - 1] == nums[j]) {
                    j++;
                    continue;
                }
                if (k + 1 < length && nums[k + 1] == nums[k]) {
                    k--;
                    continue;
                }
                if (-nums[i] == nums[j] + nums[k]) {
                    rs.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    k--;
                    j++;
                } else if (-nums[i] < nums[j] + nums[k]){
                    k--;
                }else {
                    j++;
                }
            }
        }
        return rs;
    }
    public List<List<Integer>> threeSum1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

}
