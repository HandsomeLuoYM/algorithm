package com.algorithm.two_zero_two_one.march.分割回文串II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ming
 * @date 2021/3/8 - 0:33
 * @describe
 */
public class LeetCode {
    /**
     *
     给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。

     返回符合要求的 最少分割次数 。



     示例 1：

     输入：s = "aab"
     输出：1
     解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
     示例 2：

     输入：s = "a"
     输出：0
     示例 3：

     输入：s = "ab"
     输出：1
     */
    /**
     * 自己的思路：超时
     */
    int rs = Integer.MAX_VALUE;
    List<String> now = new ArrayList<>();
    public Integer minCut(String s) {
        dfs(s, 0);
        return rs;
    }

    public void dfs(String s, int index){
        int left, right;
        if (index >= s.length()) {
            rs = Math.min(rs, now.size());
            return;
        }
        for (int i = index; i < s.length(); i++) {
            left = index;
            right = i;
            while (right > left) {
                if (s.charAt(right) == s.charAt(left)){
                    right--;
                    left++;
                }else
                    break;
            }
            if (right <= left) {
                now.add(s.substring(index, i + 1));
                dfs(s, i + 1);
                now.remove(now.size() - 1);
            }
        }
    }

    static class Solution {
        /**
         * 思路：动态规划快速找到所有的回文串，然后再用深度优先遍历所有的回文串
         * 执行用时：17 ms, 在所有 Java 提交中击败了42.69%的用户
         * 内存消耗：38.5 MB, 在所有 Java 提交中击败了35.01%的用户
         */
        public int minCut(String s) {
            int n = s.length();
            boolean[][] g = new boolean[n][n];
            for (int i = 0; i < n; ++i) {
                Arrays.fill(g[i], true);
            }
            //找到所有的回文串
            for (int i = n - 1; i >= 0; --i) {
                for (int j = i + 1; j < n; ++j) {
                    g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
                }
            }
            //贪心填充到第 n 个位置最小的个数
            int[] f = new int[n];
            Arrays.fill(f, Integer.MAX_VALUE);
            for (int i = 0; i < n; ++i) {
                if (g[0][i]) {
                    f[i] = 0;
                } else {
                    for (int j = 0; j < i; ++j) {
                        if (g[j + 1][i]) {
                            f[i] = Math.min(f[i], f[j] + 1);
                        }
                    }
                }
            }

            return f[n - 1];
        }
    }

}
