package com.algorithm.two_zero_two_one.february.字符串的排列;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Ming
 * @date 2021/2/10 - 0:55
 * @describe
 */
public class LeetCode {
    /**
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     *
     * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
     *
     * 输入: s1 = "ab" s2 = "eidbaooo"
     * 输出: True
     * 解释: s2 包含 s1 的排列之一 ("ba").
     *
     * 输入: s1= "ab" s2 = "eidboaoo"
     * 输出: False
     */
    /**
     * 思路：hash + 滑动窗口
     * 执行用时：35 ms, 在所有 Java 提交中击败了21.82%的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了5.27%的用户
     */
    public boolean checkInclusion(String s1, String s2) {
        int s1Length = s1.length(), s2Length = s2.length(), num;
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s1Length; i++){
            if (map.containsKey(s1.charAt(i))) {
                map.put(s1.charAt(i), map.get(s1.charAt(i)) + 1);
            }else {
                map.put(s1.charAt(i), 1);
            }
            set.add(s1.charAt(i));
        }

        for (int i = 0; i < s2Length; i++) {
            if (set.contains(s2.charAt(i))) {
                if (map.containsKey(s2.charAt(i))){
                    num = map.get(s2.charAt(i));
                    if (num == 1) {
                        map.remove(s2.charAt(i));
                    }else {
                        map.put(s2.charAt(i), num - 1);
                    }
                }else {
                    map.put(s2.charAt(i), -1);
                }
            }
            //调整map
            if (i - s1Length >= 0 && set.contains(s2.charAt(i - s1Length))) {
                if (map.containsKey(s2.charAt(i - s1Length))) {
                    num = map.get(s2.charAt(i - s1Length));
                    if (num == -1) {
                        map.remove(s2.charAt(i - s1Length));
                    }else {
                        map.put(s2.charAt(i - s1Length), num + 1);
                    }
                }else {
                    map.put(s2.charAt(i - s1Length), 1);
                }
            }
            if (map.isEmpty()) return true;
        }
        return false;
    }

    /**
     * 滑动窗口
     */
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            int n = s1.length(), m = s2.length();
            if (n > m) {
                return false;
            }
            int[] cnt = new int[26];
            for (int i = 0; i < n; ++i) {
                --cnt[s1.charAt(i) - 'a'];
                ++cnt[s2.charAt(i) - 'a'];
            }
            int diff = 0;
            for (int c : cnt) {
                if (c != 0) {
                    ++diff;
                }
            }
            if (diff == 0) {
                return true;
            }
            for (int i = n; i < m; ++i) {
                int x = s2.charAt(i) - 'a', y = s2.charAt(i - n) - 'a';
                if (x == y) {
                    continue;
                }
                if (cnt[x] == 0) {
                    ++diff;
                }
                ++cnt[x];
                if (cnt[x] == 0) {
                    --diff;
                }
                if (cnt[y] == 0) {
                    ++diff;
                }
                --cnt[y];
                if (cnt[y] == 0) {
                    --diff;
                }
                if (diff == 0) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 双指针
     */
    class Solution1 {
        public boolean checkInclusion(String s1, String s2) {
            int n = s1.length(), m = s2.length();
            if (n > m) {
                return false;
            }
            int[] cnt = new int[26];
            for (int i = 0; i < n; ++i) {
                --cnt[s1.charAt(i) - 'a'];
            }
            int left = 0;
            for (int right = 0; right < m; ++right) {
                int x = s2.charAt(right) - 'a';
                ++cnt[x];
                while (cnt[x] > 0) {
                    --cnt[s2.charAt(left) - 'a'];
                    ++left;
                }
                if (right - left + 1 == n) {
                    return true;
                }
            }
            return false;
        }
    }


}
