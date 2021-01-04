package com.algorithm.two_zero_two_zero.november.two_five;

/**
 * @author Ming
 * @date 2020/11/25 - 0:41
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
     *
     * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
     * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
     * 重复步骤 2 ，直到你没法从 s 中选择字符。
     * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
     * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
     * 重复步骤 5 ，直到你没法从 s 中选择字符。
     * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
     * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
     *
     * 请你返回将 s 中字符重新排序后的 结果字符串 。
     *
     * 输入：s = "aaaabbbbcccc"
     * 输出："abccbaabccba"
     * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
     * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
     * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
     * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
     * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
     *
     * 输入：s = "spo"
     * 输出："ops"
     */
    /**
     * 自己的做法：
     *      统计再排序
     * 执行用时：4 ms, 在所有 Java 提交中击败了45.23%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了82.87%的用户
     */
    public String sortString(String s) {
        int[] num = new int[26];
        int length = s.length();
        for (int i = 0; i<length; i++){
            num[s.charAt(i)-97]++;
        }
        StringBuilder result = new StringBuilder();
        while (true){
            boolean flag = true;
            for (int i = 0; i<26; i++){
                if (num[i]>0) {
                    result.append(Character.toChars(i+97));
                    num[i]--;
                    flag = false;
                }
            }
            for (int i = 25; i>=0; i--){
                if (num[i]>0) {
                    result.append(Character.toChars(i+97));
                    num[i]--;
                    flag = false;
                }
            }
            if (flag) return result.toString();
        }
    }

    /**
     * 官方题解：和我的一样
     */
    class Solution {
        public String sortString(String s) {
            int[] num = new int[26];
            for (int i = 0; i < s.length(); i++) {
                num[s.charAt(i) - 'a']++;
            }

            StringBuffer ret = new StringBuffer();
            while (ret.length() < s.length()) {
                for (int i = 0; i < 26; i++) {
                    if (num[i] > 0) {
                        ret.append((char) (i + 'a'));
                        num[i]--;
                    }
                }
                for (int i = 25; i >= 0; i--) {
                    if (num[i] > 0) {
                        ret.append((char) (i + 'a'));
                        num[i]--;
                    }
                }
            }
            return ret.toString();
        }
    }

}
