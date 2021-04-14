package com.algorithm.two_zero_two_one.march.分割回文串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ming
 * @date 2021/3/7 - 1:10
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     *
     * 返回 s 所有可能的分割方案。
     *
     * 示例:
     *
     * 输入: "aab"
     * 输出:
     * [
     *   ["aa","b"],
     *   ["a","a","b"]
     * ]
     *
     */
    /**
     * 回溯 + 递归
     */
    boolean[][] f;
    List<List<String>> ret = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    static class Solution{
        /**
         * 自己的思路：回溯暴力
         * 执行用时：9 ms, 在所有 Java 提交中击败了89.87%的用户
         * 内存消耗：52.3 MB, 在所有 Java 提交中击败了52.40%的用户
         */
        List<List<String>> rs = new ArrayList<>();
        List<String> now = new ArrayList<>();
        public List<List<String>> partition(String s) {
            dfs(s, 0);
            return rs;
        }

        public void dfs(String s, int index){
            int left, right;
            if (index >= s.length()) {
                rs.add(new ArrayList<>(now));
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

    }
}
