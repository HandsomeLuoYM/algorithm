package com.algorithm.december.单词规律;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming
 * @date 2020/12/16 - 0:48
 * @describe
 */
public class LeetCode {
    /**
     * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
     *
     * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
     *
     * 输入: pattern = "abba", str = "dog cat cat dog"
     * 输出: true
     *
     * 输入:pattern = "abba", str = "dog cat cat fish"
     * 输出: false
     *
     * 输入: pattern = "aaaa", str = "dog cat cat dog"
     * 输出: false
     *
     * 输入: pattern = "abba", str = "dog dog dog dog"
     * 输出: false
     */
    /**
     * 自己的做法：（答案是用两个hash表维护，思路一样）
     *      hash + 数组，存储每个单词对应的字符，也记录每个字符对应的单词，然后再判断
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.94%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了95.91%的用户
     */
    public boolean wordPattern(String pattern, String s) {
        String[] ss = s.split(" ");
        if (pattern.length() != ss.length) return false;
        String[] sr = new String[26];
        Map<String,Character> map = new HashMap<>();
        int index = 0;
        for (String s1 : ss) {
            if (null == sr[pattern.charAt(index)-'a']){
                if (map.containsKey(s1)) return false;
                sr[pattern.charAt(index)-'a'] = s1;
                map.put(s1,pattern.charAt(index));
            }else {
                if (!sr[pattern.charAt(index)-'a'].equals(s1)||!map.get(s1).equals(pattern.charAt(index))){
                    return false;
                }
            }
            index++;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        System.out.println(l.wordPattern("abc", "caa aa caa"));
    }

}
