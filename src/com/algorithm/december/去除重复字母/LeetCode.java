package com.algorithm.december.去除重复字母;

/**
 * @author Ming
 * @date 2020/12/20 - 1:07
 * @describe
 */
public class LeetCode {

    /**
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     *
     * 输入：s = "bcabc"
     * 输出："abc"
     */
    class Solution {
        public String removeDuplicateLetters(String s) {
            boolean[] vis = new boolean[26];
            int[] num = new int[26];
            for (int i = 0; i < s.length(); i++) {
                num[s.charAt(i) - 'a']++;
            }

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (!vis[ch - 'a']) {
                    while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                        if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                            vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                            sb.deleteCharAt(sb.length() - 1);
                        } else {
                            break;
                        }
                    }
                    vis[ch - 'a'] = true;
                    sb.append(ch);
                }
                num[ch - 'a'] -= 1;
            }
            return sb.toString();
        }
    }
}
