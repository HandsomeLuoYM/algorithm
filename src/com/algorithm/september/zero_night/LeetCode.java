package com.algorithm.september.zero_night;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ming
 * @date 2020/9/9 - 1:08
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     */
    /**
     * 自己的解法（优化过的）
     *
     * 思路：一开始并没有进行排序，然后遍历candidates每一个元素，然后添加当前list(存放遍历的点)，
     *      当等于目标时添加到lists（存放答案）中，但是发现会存在相同的答案，因为你无法确定后面是否还有满足的情况，
     *      所以就想到先用排序，然后需要再传一个参数来标记你已经遍历到的位置（防止出现相同元素的答案出现在lists中），
     *      当当前的值加上目前List中的总值大于或等于target时就可以确定后面没有满足的情况了，进而进行剪枝。
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了87.50%的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了18.16%的用户
     */
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
        return lists;
    }

    /**
     * @param list 存放当前存在元素的队列
     * @param candidates 源数组
     * @param target 目标数
     * @param all 目前list中所有数的和
     * @param length candidates的长度
     * @param now 现在进行到的candidates的位置
     */
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
                lists.add(new ArrayList<>(list));
                list.remove(list.size()-1);
                return;
            }
            //回溯
            add(list,candidates,target,all,length,i);
            list.remove(list.size()-1);
            all-=candidates[i];
        }
    }

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();

        List<List<Integer>> lists = leetCode.combinationSum(new int[]{5, 7, 4, 6,8}, 13);
        lists.forEach(list -> {
            System.out.println(list.toString());
        });
    }
}
