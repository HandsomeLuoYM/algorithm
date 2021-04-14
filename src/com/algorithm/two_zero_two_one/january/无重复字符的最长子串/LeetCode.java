package com.algorithm.two_zero_two_one.january.无重复字符的最长子串;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Ming
 * @date 2021/1/20 - 21:07
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *      执行用时：6 ms, 在所有 Java 提交中击败了86.06%的用户
     *      内存消耗：38.8 MB, 在所有 Java 提交中击败了32.68%的用户
     * 快慢指针
     */
    public int lengthOfLongestSubstring(String s) {
        int low = 0, length = s.length(), max = 0;
        if (length == 0) return 0;
        Set<Character> set = new HashSet<Character>();
        char ch;
        for (int i = 0; i < length; i++){
            ch = s.charAt(i);
            if (set.contains(ch)) {
                while (ch != s.charAt(low)) {
                    set.remove(s.charAt(low));
                    low++;
                }
                low++;
            }else {
                max = Math.max(max, i - low + 1);
            }
            set.add(ch);
        }
        return max;
    }
}
