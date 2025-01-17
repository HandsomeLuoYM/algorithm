package com.algorithm.two_zero_two_one.march.不同的子序列;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ming
 * @date 2021/3/17 - 11:40
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     *
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     *
     * 题目数据保证答案符合 32 位带符号整数范围。
     *
     * 示例 1：
     *
     * 输入：s = "rabbbit", t = "rabbit"
     * 输出：3
     * 解释：
     * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
     * (上箭头符号 ^ 表示选取的字母)
     * rabbbit
     * ^^^^ ^^
     * rabbbit
     * ^^ ^^^^
     * rabbbit
     * ^^^ ^^^
     * 示例 2：
     *
     * 输入：s = "babgbag", t = "bag"
     * 输出：5
     * 解释：
     * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
     * (上箭头符号 ^ 表示选取的字母)
     * babgbag
     * ^^ ^
     * babgbag
     * ^^    ^
     * babgbag
     * ^    ^^
     * babgbag
     *   ^  ^^
     * babgbag
     *     ^^^
     *
     */
    /**
     * 思路：记录前面出现的次数
     * 执行用时：2 ms, 在所有 Java 提交中击败了97.64%的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了67.76%的用户
     */
    public int numDistinct(String s, String t) {
        Map<Character, List<Integer>> map = new HashMap<>();
        int tLength = t.length(), sLength = s.length();
        for (int i = tLength - 1; i >= 0; i--) {
            if (!map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), new ArrayList<Integer>());
            }
            map.get(t.charAt(i)).add(i);
        }
        int[] number = new int[tLength];
        List<Integer> nowIndex;
        for (int i = 0; i < sLength; i++) {
            if (map.containsKey(s.charAt(i))) {
                nowIndex = map.get(s.charAt(i));
                for (Integer index : nowIndex) {
                    if (index == 0) number[0]++;
                    else {
                        if (number[index - 1] != 0) {
                            number[index] = number[index] + number[index - 1];
                        }
                    }
                }
            }
        }
        return number[tLength - 1];
    }
}
