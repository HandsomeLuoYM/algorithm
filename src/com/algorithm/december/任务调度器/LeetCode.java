package com.algorithm.december.任务调度器;

import java.util.*;

/**
 * @author Ming
 * @date 2020/12/5 - 10:16
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。
     * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
     *
     * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，
     * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     *
     * 你需要计算完成所有任务所需要的 最短时间 。
     *
     * 输入：tasks = ["A","A","A","B","B","B"], n = 2
     * 输出：8
     * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
     *      在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
     *
     *
     * 输入：tasks = ["A","A","A","B","B","B"], n = 0
     * 输出：6
     * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
     * ["A","A","A","B","B","B"]
     * ["A","B","A","B","A","B"]
     * ["B","B","B","A","A","A"]
     *
     *
     * 输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
     * 输出：16
     * 解释：一种可能的解决方案是：
     *      A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
     */
    /**
     * 自己的思路：
     *      先统计个数，然后分情况处理（和官方题解二一样）
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了41.42%的用户
     */
    public int leastInterval(char[] tasks, int n) {
        int length = tasks.length;
        int[] number = new int[26];
        //统计个数
        for(int i = 0; i<length; i++){
            number[tasks[i]-65]++;
        }
        //找到最大值
        int max = Integer.MIN_VALUE, maxNumber = 0;
        for (int i = 0; i<26; i++){
            if (number[i]>max) {
                max = number[i];
                maxNumber = 1;
            } else if (number[i]==max){
                maxNumber++;
            }
        }
        if ((n+1)*(max-1)+maxNumber < length) return length;
        else return (n+1)*(max-1)+maxNumber;
    }

    /**
     * 官方题解一：模拟
     */
    class Solution {
        public int leastInterval(char[] tasks, int n) {
            Map<Character, Integer> freq = new HashMap<Character, Integer>();
            for (char ch : tasks) {
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }

            // 任务总数
            int m = freq.size();
            List<Integer> nextValid = new ArrayList<Integer>();
            List<Integer> rest = new ArrayList<Integer>();
            Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                int value = entry.getValue();
                nextValid.add(1);
                rest.add(value);
            }

            int time = 0;
            for (int i = 0; i < tasks.length; ++i) {
                ++time;
                int minNextValid = Integer.MAX_VALUE;
                for (int j = 0; j < m; ++j) {
                    if (rest.get(j) != 0) {
                        minNextValid = Math.min(minNextValid, nextValid.get(j));
                    }
                }
                time = Math.max(time, minNextValid);
                int best = -1;
                for (int j = 0; j < m; ++j) {
                    if (rest.get(j) != 0 && nextValid.get(j) <= time) {
                        if (best == -1 || rest.get(j) > rest.get(best)) {
                            best = j;
                        }
                    }
                }
                nextValid.set(best, time + n + 1);
                rest.set(best, rest.get(best) - 1);
            }

            return time;
        }
    }

}
