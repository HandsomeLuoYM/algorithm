package com.algorithm.november.two_two;

import java.util.Arrays;

/**
 * @author Ming
 * @date 2020/11/22 - 1:28
 * @describe 242. 有效的字母异位词
 */
public class LeetCode {
    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     *
     * 输入: s = "rat", t = "car"
     * 输出: false
     *
     * 说明:
     * 你可以假设字符串只包含小写字母。
     *
     * 进阶:
     * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     */
    /**
     * 自己的思路：
     *      设置一个数组来存放目前的消除情况，最后只需要判断数组即可
     * 执行用时：4 ms, 在所有 Java 提交中击败了64.49%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了95.14%的用户
     */
    public boolean isAnagram(String s, String t) {
        if (s.length()!=t.length()) return false;
        int[] num = new int[26];
        int length = s.length();
        for (int i = 0; i<length; i++){
            num[s.charAt(i)-97]++;
            num[t.charAt(i)-97]--;
        }
        for (int i = 0; i<26; i++){
            if (num[i]!=0) return false;
        }
        return true;
    }

    /**
     * 官方题解一
     */
    class Solution {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            char[] str1 = s.toCharArray();
            char[] str2 = t.toCharArray();
            Arrays.sort(str1);
            Arrays.sort(str2);
            return Arrays.equals(str1, str2);
        }
    }

}
