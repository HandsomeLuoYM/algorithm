package com.algorithm.two_zero_two_one.february.替换后的最长重复字符;

/**
 * @author Ming
 * @date 2021/2/2 - 15:23
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
     *
     * 注意：字符串长度 和 k 不会超过 104。
     *
     * 输入：s = "ABAB", k = 2
     * 输出：4
     * 解释：用两个'A'替换为两个'B',反之亦然。
     *
     * 输入：s = "AABABBA", k = 1
     * 输出：4
     * 解释：
     * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
     * 子串 "BBBB" 有最长重复字母, 答案为 4。
     *
     */
    /**
     * 贪心的思想：一直维护一个到当前最大的窗口
     *
     * 执行用时：7 ms, 在所有 Java 提交中击败了46.40%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了45.02%的用户
     */
    public int characterReplacement(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        int left = 0, right = 0;
        while (right < n) {
            num[s.charAt(right) - 'A']++;
            maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
            if (right - left + 1 - maxn > k) {
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }

}
