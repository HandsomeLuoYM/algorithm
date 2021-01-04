package com.algorithm.two_zero_two_zero.december.同构字符串;

/**
 * @author Ming
 * @date 2020/12/27 - 1:11
 * @describe 同构字符串
 */
public class LeetCode {
    /**
     * 给定两个字符串 s 和 t，判断它们是否是同构的。
     *
     * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
     *
     * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
     *
     * 示例 1:
     * 输入: s = "egg", t = "add"
     * 输出: true
     *
     * 输入: s = "foo", t = "bar"
     * 输出: false
     */
    /**
     * 思路：hash
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.51%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了83.98%的用户
     */
    public boolean isIsomorphic(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        int[] preIndexOfs = new int[256];
        int[] preIndexOft = new int[256];
        for (int i = 0; i < chars.length; i++) {
            if (preIndexOfs[chars[i]] != preIndexOft[chart[i]]) {
                return false;
            }
            preIndexOfs[chars[i]] = i + 1;
            preIndexOft[chart[i]] = i + 1;
        }
        return true;
    }
}
