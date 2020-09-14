package com.algorithm.september.one_zero;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ming
 * @date 2020/9/10 - 13:29
 * @describe 40. 组合总和 II
 */
public class LeetCode {

    /**
     *
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     *
     * 说明：
     *
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。
     *
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为:
     * [
     *   [1,2,2],
     *   [5]
     * ]
     */

    /**
     * 自己的解法
     * 排序+回溯+剪枝
     * 执行用时：5 ms, 在所有 Java 提交中击败了45.03%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了88.86%的用户
     */
    Set<List<Integer>> set = new HashSet<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int length = candidates.length;
        //按从小到大排序
        for (int i=0;i<length;i++){
            int temp=i;
            for (int j=i+1;j<length;j++){
                if (candidates[temp]>candidates[j]) temp = j;
            }
            if (temp!=i){
                int swag = candidates[i];
                candidates[i]=candidates[temp];
                candidates[temp]=swag;
            }
        }
        add(new ArrayList(),candidates,target,0,candidates.length,0);
        return new ArrayList<>(set);
    }

    public void add(List list,int[] candidates, int target,int all,int length,int now){
        //从i开始遍历，避免有重复元素的数组出现，用Set则无法存储相同的数
        for (int i =now;i<length;i++){
            //如果已经超过了target则需要回溯
            if (all+candidates[i]>target) return;
            //重新计算all值
            all+=candidates[i];
            //添加到数组
            list.add(candidates[i]);
            //如果目前数以达到target，则添加后返回，因为是递增数组，所以后面不存在满足的数了
            if (all==target){
                set.add(new ArrayList<>(list));
                list.remove(list.size()-1);
                return;
            }
            //回溯
            add(list,candidates,target,all,length,i+1);
            list.remove(list.size()-1);
            all-=candidates[i];
        }
    }

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        List<List<Integer>> lists = leetCode.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        lists.forEach(list -> {
            System.out.println(list);
        });
    }

}
