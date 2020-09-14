package com.algorithm.september.zero_eight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/8 - 13:20
 * @describe 77. 组合
 */
public class LeetCode {

    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * 输入: n = 4, k = 2
     * 输出:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     */
    /**
     * 自己的解法，递归枚举解决
     * 执行用时：23 ms, 在所有 Java 提交中击败了53.96%的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了76.03%的用户
     */
    List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n<k) return lists;
        dfs(new ArrayList<Integer>(),1,0,k,n);
        return lists;
    }

    /**
     *
     * @param list 目前队列
     * @param now 现在进行到的值
     * @param i 第几个值了
     * @param n 值的上限
     * @param k 总共需要k个值
     */
    public void dfs(List list, int now, int i, int k,int n){
        if (i==k) {
            lists.add(new ArrayList<>(list));
            return;
        }
        int j = now;
        while (j<=n){
            list.add(j);
            dfs(list,j+1,i+1,k,n);
            list.remove(i);
            j++;
        }
    }
}
