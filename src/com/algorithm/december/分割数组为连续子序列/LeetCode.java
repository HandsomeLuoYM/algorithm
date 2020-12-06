package com.algorithm.december.分割数组为连续子序列;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Ming
 * @date 2020/12/4 - 10:17
 * @describe 659. 分割数组为连续子序列
 */
public class LeetCode {

    /**
     * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
     *
     * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
     *
     * 输入: [1,2,3,3,4,5]
     * 输出: True
     * 解释:
     * 你可以分割出这样两个连续子序列 :
     * 1, 2, 3
     * 3, 4, 5
     */
    /**
     * 贪心思想算法
     *      构造最简单的子序列，再将零散的拼接到后面去
     * 执行用时：31 ms, 在所有 Java 提交中击败了58.59%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了54.08%的用户
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        //存放结束的数和以该数结束的数的数组大小
        Map<Integer, Integer> endMap = new HashMap<Integer, Integer>();
        //统计个数
        for (int x : nums) {
            int count = countMap.getOrDefault(x, 0) + 1;
            countMap.put(x, count);
        }
        for (int x : nums) {
            int count = countMap.getOrDefault(x, 0);
            if (count > 0) {
                //可以找到托付的地方
                int prevEndCount = endMap.getOrDefault(x - 1, 0);
                //前面有数
                if (prevEndCount > 0) {
                    //数量减一
                    countMap.put(x, count - 1);
                    //拼凑到后面
                    endMap.put(x - 1, prevEndCount - 1);
                    endMap.put(x, endMap.getOrDefault(x, 0) + 1);
                } else {
                    //查看能否构造一个新的子序列
                    int count1 = countMap.getOrDefault(x + 1, 0);
                    int count2 = countMap.getOrDefault(x + 2, 0);
                    if (count1 > 0 && count2 > 0) {
                        //构造新的子序列
                        countMap.put(x, count - 1);
                        countMap.put(x + 1, count1 - 1);
                        countMap.put(x + 2, count2 - 1);
                        endMap.put(x + 2, endMap.getOrDefault(x + 2, 0) + 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 哈希表 + 最小堆
     */
    class Solution {
        public boolean isPossible(int[] nums) {
            //存放该数结束的数以及长度队列（堆）
            Map<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();
            for (int x : nums) {
                if (!map.containsKey(x)) {
                    map.put(x, new PriorityQueue<Integer>());
                }
                if (map.containsKey(x - 1)) {
                    //取出x-1最短的那个，然后放到x中，长度加一
                    int prevLength = map.get(x - 1).poll();
                    if (map.get(x - 1).isEmpty()) {
                        map.remove(x - 1);
                    }
                    map.get(x).offer(prevLength + 1);
                } else {
                    //没有x-1就得构造一个新的，并且长度为 1
                    map.get(x).offer(1);
                }
            }
            //判断是否有小于 3 的子序列
            Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
            for (Map.Entry<Integer, PriorityQueue<Integer>> entry : entrySet) {
                PriorityQueue<Integer> queue = entry.getValue();
                if (queue.peek() < 3) {
                    return false;
                }
            }
            return true;
        }
    }

}
