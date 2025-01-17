package com.algorithm.two_zero_two_zero.december.分发饼干;

import java.util.Arrays;

/**
 * @author Ming
 * @date 2020/12/25 - 0:08
 * @describe
 */
public class LeetCode {

    /**
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     *
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。
     * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * 输入: g = [1,2,3], s = [1,1]
     * 输出: 1
     * 解释:
     * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
     * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
     * 所以你应该输出1。
     *
     * 输入: g = [1,2], s = [1,2,3]
     * 输出: 2
     * 解释:
     * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
     * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
     * 所以你应该输出2.
     */
    /**
     * 思路（贪心算法）：（和官方题解一样）
     *      先都排序，然后再优化判断
     * 执行用时：7 ms, 在所有 Java 提交中击败了99.54%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了72.08%的用户
     */
    public int findContentChildren(int[] g, int[] s) {
        int gLength = g.length, sLength = s.length;
        Arrays.sort(g);
        Arrays.sort(s);
        int sIndex = 0, gIndex = 0;
        while (gIndex < gLength){
            while (sIndex < sLength && s[sIndex] < g[gIndex]){
                sIndex++;
            }
            if (sLength <= sIndex) return gIndex;
            sIndex++;
            gIndex++;
        }
        return gIndex;
    }

    class Solution {
        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);
            Arrays.sort(s);
            int numOfChildren = g.length, numOfCookies = s.length;
            int count = 0;
            for (int i = 0, j = 0; i < numOfChildren && j < numOfCookies; i++, j++) {
                while (j < numOfCookies && g[i] > s[j]) {
                    j++;
                }
                if (j < numOfCookies) {
                    count++;
                }
            }
            return count;
        }
    }

}
