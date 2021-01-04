package com.algorithm.two_zero_two_zero.october.two_eight;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Ming
 * @date 2020/10/28 - 0:17
 * @describe
 */
public class LeetCode {

    /**
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     *
     * 输入：arr = [1,2,2,1,1,3]
     * 输出：true
     * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
     *
     * 输入：arr = [1,2]
     * 输出：false
     *
     * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
     * 输出：true
     */
    /**
     * 自己的思路一：
     *      利用两个Map来存储信息，一个存点的数量，一个存某个数量下数的个数
     * 执行用时：5 ms, 在所有 Java 提交中击败了8.28%的用户
     * 内存消耗：37.6 MB, 在所有 Java 提交中击败了13.77%的用户
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>(),numMap = new HashMap<>();
        int length = arr.length;
        for (int i =0; i<length; i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
            numMap.put(map.get(arr[i]),numMap.getOrDefault(map.get(arr[i]),0)+1);
            numMap.put(map.get(arr[i])-1,numMap.getOrDefault(map.get(arr[i])-1,1)-1);
        }
        for (Map.Entry<Integer,Integer> entry : numMap.entrySet()){
            if (entry.getValue()>1) return false;
        }
        return true;
    }

    /**
     * 自己的思路二（和官方题解思路一致）：
     *      利用map存储数的个数，最后用set来存储数有的数量，当set里面存在改数量即有两个一样的
     * 执行用时：2 ms, 在所有 Java 提交中击败了91.42%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了98.02%的用户
     */
    public boolean uniqueOccurrences1(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        int length = arr.length;
        for (int i =0; i<length; i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            if (set.contains(entry.getValue())) return false;
            else set.add(entry.getValue());
        }
        return true;
    }

}
