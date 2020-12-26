package com.algorithm.december.找不同;

/**
 * @author Ming
 * @date 2020/12/18 - 0:41
 * @describe
 */
public class LeetCode {
    /**
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     * 请找出在 t 中被添加的字母。
     *
     * 输入：s = "abcd", t = "abcde"
     * 输出："e"
     * 解释：'e' 是那个被添加的字母。
     */
    /**
     * 自己的思路：
     *      统计然后判断。
     * 执行用时：2 ms, 在所有 Java 提交中击败了76.78%的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了58.63%的用户
     */
    public char findTheDifference(String s, String t) {
        int[] num = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++){
            num[s.charAt(i)-'a']++;
        }
        length++;
        for (int i = 0; i < length; i++){
            if (--num[t.charAt(i)-'a']==-1) return t.charAt(i);
        }
        return 'a';
    }

    /**
     * 求和：思路新颖
     */
    class Solution {
        public char findTheDifference(String s, String t) {
            int as = 0, at = 0;
            for (int i = 0; i < s.length(); ++i) {
                as += s.charAt(i);
            }
            for (int i = 0; i < t.length(); ++i) {
                at += t.charAt(i);
            }
            return (char) (at - as);
        }
    }

    /**
     * 位运算
     */
    class Solution1 {
        public char findTheDifference(String s, String t) {
            int ret = 0;
            for (int i = 0; i < s.length(); ++i) {
                ret ^= s.charAt(i);
            }
            for (int i = 0; i < t.length(); ++i) {
                ret ^= t.charAt(i);
            }
            return (char) ret;
        }
    }

}
