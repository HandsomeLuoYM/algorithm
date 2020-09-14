package com.algorithm.august.three_zero;

import java.util.Arrays;

/**
 * @author Ming
 * @date 2020/8/30 - 10:01
 * @describe 557. 反转字符串中的单词 III
 */
public class LeetCode {

    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     *
     * 输入："Let's take LeetCode contest"
     * 输出："s'teL ekat edoCteeL tsetnoc"
     */


    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了88.46%的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了72.24%的用户
     */
    public String reverseWords(String s) {
        StringBuffer all = new StringBuffer();
        String[] split = s.split(" ");
        for (String st : split){
            all.append(new StringBuffer(st).reverse().append(" "));
        }
        return all.toString().trim();
    }

    ////////////  官方题解一  /////////////
    class Solution {
        public String reverseWords(String s) {
            StringBuffer ret = new StringBuffer();
            int length = s.length();
            int i = 0;
            while (i < length) {
                int start = i;
                while (i < length && s.charAt(i) != ' ') {
                    i++;
                }
                for (int p = start; p < i; p++) {
                    ret.append(s.charAt(start + i - 1 - p));
                }
                while (i < length && s.charAt(i) == ' ') {
                    i++;
                    ret.append(' ');
                }
            }
            return ret.toString();
        }
    }

}
