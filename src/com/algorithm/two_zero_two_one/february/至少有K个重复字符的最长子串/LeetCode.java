package com.algorithm.two_zero_two_one.february.至少有K个重复字符的最长子串;

import java.util.*;

/**
 * @author Ming
 * @date 2021/2/27 - 0:43
 * @describe
 */
public class LeetCode {
    /**
     * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
     *
     * 示例 1:
     *
     * 输入:
     * s = "aaabb", k = 3
     *
     * 输出:
     * 3
     *
     * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
     * 示例 2:
     *
     * 输入:
     * s = "ababbc", k = 2
     *
     * 输出:
     * 5
     *
     * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     */
    /**
     * 思路：统计个数，然后递归求解
     * 执行用时：5 ms, 在所有 Java 提交中击败了30.01%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了19.63%的用户
     */
    private int rs = 0;
    public int longestSubstring(String s, int k) {
        if (s.length() < k) return rs;
        Map<Character, List<Integer>> map = new HashMap<>();
        List<Integer> list;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.get(s.charAt(i)).add(i);
            }else {
                list = new LinkedList<>();
                list.add(i);
                map.put(s.charAt(i), list);
            }
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((o1, o2) -> {
            return o1-o2;
        });
        for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() < k) {
                queue.addAll(entry.getValue());
            }
        }
        if (queue.isEmpty()) {
            rs = Math.max(rs, s.length());
            return rs;
        }
        int last = 0;
        while (!queue.isEmpty()) {
            longestSubstring(s.substring(last, queue.peek()), k);
            last = queue.poll() + 1;
        }
        if (last < s.length()) longestSubstring(s.substring(last, s.length()), k);
        return rs;
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        System.out.println(l.longestSubstring("weitong",
                2));
    }
}
