package com.algorithm.two_zero_two_one.february.尽可能使字符串相等;

/**
 * @author Ming
 * @date 2021/2/5 - 1:37
 * @describe
 */
public class LeetCode {
    /**
     * 给你两个长度相同的字符串，s 和 t。
     *
     * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
     *
     * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
     *
     * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
     *
     * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
     *
     * 输入：s = "abcd", t = "bcdf", cost = 3
     * 输出：3
     * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
     *
     * 输入：s = "abcd", t = "cdef", cost = 3
     * 输出：1
     * 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
     *
     */
    /**
     * 思路：滑动窗口，设置一个指针来存储当前到达的位置，再边遍历边修改值（也可以一直维护一个最大窗口）
     * 执行用时：12 ms, 在所有 Java 提交中击败了14.20%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了45.68%的用户
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int length = Math.min(s.length(),  t.length()), rs = 0, nowCost = 0, index = 0, cost;
        for (int i = 0; i < length; i++){
            cost = Math.abs(s.charAt(i) - t.charAt(i));
            if (cost > maxCost) {
                nowCost = 0;
                index = i + 1;
                continue;
            }
            if (nowCost + cost <= maxCost) {
                nowCost += cost;
            } else {
                while (index < i && nowCost + cost > maxCost) {
                    nowCost -= Math.abs(s.charAt(index) - t.charAt(index));
                    index++;
                }
                nowCost += cost;
            }
            rs = Math.max(rs, i - index);
        }
        return rs;
    }

    /**
     * 我的思路2 ：
     *      维护一个最大窗口
     * 执行用时：10 ms, 在所有 Java 提交中击败了28.87%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了66.82%的用户
     */
    public int equalSubstring1(String s, String t, int maxCost) {
        int length = Math.min(s.length(),  t.length()), rs = 0, nowCost = 0, index = 0, cost;
        for (int i = 0; i < length; i++){
            cost = Math.abs(s.charAt(i) - t.charAt(i));
            if (nowCost + cost <= maxCost) {
                nowCost += cost;
            } else {
                nowCost = nowCost - Math.abs(s.charAt(index) - t.charAt(index)) + cost;
                index++;
            }
            rs = Math.max(rs, i - index);
        }
        return rs;
    }

    /**
     * 思路：二分查找
     */
    class Solution {
        public int equalSubstring(String s, String t, int maxCost) {
            int n = s.length();
            int[] accDiff = new int[n + 1];
            //累计前面所有的和
            for (int i = 0; i < n; i++) {
                accDiff[i + 1] = accDiff[i] + Math.abs(s.charAt(i) - t.charAt(i));
            }
            int maxLength = 0;
            //遍历所有节点，然后二分查找最远的节点
            for (int i = 1; i <= n; i++) {
                int start = binarySearch(accDiff, i, accDiff[i] - maxCost);
                maxLength = Math.max(maxLength, i - start);
            }
            return maxLength;
        }

        public int binarySearch(int[] accDiff, int endIndex, int target) {
            int low = 0, high = endIndex;
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (accDiff[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }

}
