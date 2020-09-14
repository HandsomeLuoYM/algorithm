package com.algorithm.august.two_five;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ming
 * @date 2020/8/25 - 10:35
 * @describe  491
 */
public class LeetCode {

    /**
     * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     *
     * 给定数组的长度不会超过15。
     * 数组中的整数范围是 [-100,100]。
     * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
     *
     * 输入: [4, 6, 7, 7]
     * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
     *
     */

    /**
     * 自己的做法(用List时超时，用set进行优化)
     * 执行用时：16 ms, 在所有 Java 提交中击败了37.45%的用户
     * 内存消耗：47.5 MB, 在所有 Java 提交中击败了34.87%的用户
     */
    Set all = new HashSet<ArrayList<Integer>>();
    List<Integer> list = new ArrayList<Integer>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        add(0,nums);
        //存储Set集
        ArrayList<List<Integer>> lists = new ArrayList<>();
        lists.addAll(all);
        return lists;
    }

    private void add(int index,int[] nums){
        //数组越界判断
        if (nums.length<=index) return;
        //空队列直接添加
        if (list.isEmpty()){
            list.add(nums[index]);
            //递归
            add(index+1,nums);
            list.remove(list.size()-1);
        } else if (list.get(list.size()-1)<=nums[index]){
            //添加进入数组（数组长度>2，添加到set集）
            list.add(nums[index]);
            all.add(new ArrayList<>(list));
            //递归
            add(index+1,nums);
            list.remove(list.size()-1);
        }
        add(index+1,nums);
    }

    //////////////////////////////////////  官方题解一   ////////////////////////////////////////

    /**
     * 我们可以采取最朴素的思路，即枚举出所有的子序列，然后判断当前的子序列是否是非严格递增的。
     * 那么我们可以用什么办法来枚举所有的子序列呢？我们需要从原序列中选出一些数，来形成新的序列，
     * 即原序列的子序列。对于原序列的每一个数来说，都有两种可能的状态，即被选中或者不被选中。
     * 如果我们用 1 代表被选中，0 代表不被选中，假设一个序列长度为 3，那么选出的子序列就对应着八种状态
     *
     * 每次我们找到一个合法序列的时候，都去计算这个序列的哈希值，用一个哈希表来记录已有的哈希值，
     * 如果该值已经出现在哈希表中，就舍弃这个序列，否则就把这个序列加入到答案中。
     *
     */
    class Solution {
        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Set<Integer> set = new HashSet<Integer>();
        int n;

        public List<List<Integer>> findSubsequences(int[] nums) {
            n = nums.length;
            for (int i = 0; i < (1 << n); ++i) {
                findSubsequences(i, nums);
                int hashValue = getHash(263, (int) 1E9 + 7);
                if (check() && !set.contains(hashValue)) {
                    ans.add(new ArrayList<Integer>(temp));
                    set.add(hashValue);
                }
            }
            return ans;
        }

        public void findSubsequences(int mask, int[] nums) {
            temp.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & 1) != 0) {
                    temp.add(nums[i]);
                }
                mask >>= 1;
            }
        }

        public int getHash(int base, int mod) {
            int hashValue = 0;
            for (int x : temp) {
                hashValue = hashValue * base % mod + (x + 101);
                hashValue %= mod;
            }
            return hashValue;
        }

        public boolean check() {
            for (int i = 1; i < temp.size(); ++i) {
                if (temp.get(i) < temp.get(i - 1)) {
                    return false;
                }
            }
            return temp.size() >= 2;
        }
    }

    //////////////////////////////  官方题解二  /////////////////////////////////////

    /**
     * 做法和我的差不多
     */
    class Solution1 {
        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        public List<List<Integer>> findSubsequences(int[] nums) {
            dfs(0, Integer.MIN_VALUE, nums);
            return ans;
        }

        /**
         *
         * @param cur 当前的索引值
         * @param last 最后一个数的值
         */
        public void dfs(int cur, int last, int[] nums) {
            if (cur == nums.length) {
                if (temp.size() >= 2) {
                    ans.add(new ArrayList<Integer>(temp));
                }
                return;
            }
            if (nums[cur] >= last) {
                temp.add(nums[cur]);
                dfs(cur + 1, nums[cur], nums);
                temp.remove(temp.size() - 1);
            }
            if (nums[cur] != last) {
                dfs(cur + 1, last, nums);
            }
        }
    }

}
