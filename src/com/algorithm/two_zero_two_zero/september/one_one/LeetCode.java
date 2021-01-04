package com.algorithm.two_zero_two_zero.september.one_one;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/12 - 0:52
 * @describe 216 组合总和 III
 */
public class LeetCode {
    /**
     *找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     *
     * 说明：
     *
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     *
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 示例 2:
     *
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     */
    /**
     * 穷举
     */
    class Solution {
        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        public List<List<Integer>> combinationSum3(int k, int n) {
            dfs(1, 9, k, n);
            return ans;
        }

        public void dfs(int cur, int n, int k, int sum) {
            if (temp.size() + (n - cur + 1) < k || temp.size() > k) {
                return;
            }
            if (temp.size() == k) {
                int tempSum = 0;
                for (int num : temp) {
                    tempSum += num;
                }
                if (tempSum == sum) {
                    ans.add(new ArrayList<Integer>(temp));
                    return;
                }
            }
            temp.add(cur);
            dfs(cur + 1, n, k, sum);
            temp.remove(temp.size() - 1);
            dfs(cur + 1, n, k, sum);
        }

    }
}
