package com.algorithm.december.字符串中的第一个唯一字符;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming
 * @date 2020/12/23 - 12:38
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     *
     * s = "leetcode"
     * 返回 0
     *
     * s = "loveleetcode"
     * 返回 2
     */
    /**
     * 自己的做法：
     *      先收集每个字符的次数和第一次出现的次数，然后再修改记录值，最后遍历记录值然后返回
     * 执行用时：8 ms, 在所有 Java 提交中击败了72.75%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了69.53%的用户
     */
    public int firstUniqChar(String s) {
        int[] number = new int[26];
        Arrays.fill(number, -1);
        int length = s.length();
        for (int i = 0; i < length; i++){
            number[s.charAt(i) - 'a'] = number[s.charAt(i) - 'a'] == -1 ? i : Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++){
             min = number[i] == -1 ? min : Integer.min(min, number[i]);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 使用哈希表存储频数
     * 统计个数，然后再找第一个出现的
     */
    class Solution {
        public int firstUniqChar(String s) {
            Map<Character, Integer> frequency = new HashMap<Character, Integer>();
            for (int i = 0; i < s.length(); ++i) {
                char ch = s.charAt(i);
                frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
            }
            for (int i = 0; i < s.length(); ++i) {
                if (frequency.get(s.charAt(i)) == 1) {
                    return i;
                }
            }
            return -1;
        }
    }

    /**
     * 使用哈希表存储索引
     */
    class Solution1 {
        public int firstUniqChar(String s) {
            Map<Character, Integer> position = new HashMap<Character, Integer>();
            int n = s.length();
            for (int i = 0; i < n; ++i) {
                char ch = s.charAt(i);
                if (position.containsKey(ch)) {
                    position.put(ch, -1);
                } else {
                    position.put(ch, i);
                }
            }
            int first = n;
            for (Map.Entry<Character, Integer> entry : position.entrySet()) {
                int pos = entry.getValue();
                if (pos != -1 && pos < first) {
                    first = pos;
                }
            }
            if (first == n) {
                first = -1;
            }
            return first;
        }
    }

}
