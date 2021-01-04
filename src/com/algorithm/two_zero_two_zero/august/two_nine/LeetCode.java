package com.algorithm.two_zero_two_zero.august.two_nine;

import java.util.Arrays;

/**
 * @author Ming
 * @date 2020/8/29 - 13:07
 * @describe 214. 最短回文串
 */
public class LeetCode {
    /**
     * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
     *
     * 输入: "aacecaaa"
     * 输出: "aaacecaaa"
     *
     * 输入: "abcd"
     * 输出: "dcbabcd"
     */


    /**
     * 执行用时：498 ms, 在所有 Java 提交中击败了5.11%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了94.83%的用户
     */
    public String shortestPalindrome(String s) {

        int length = s.length(),half = length/2, i=0,flag=1;
        //本身为中心为回文串
       if (length%2==0){
           while (i<half){
               if (s.charAt(half+i)!=s.charAt(half-i-1)) break;
               i++;
           }
           if (s.equals("")||(half==i&&s.charAt(half+i-1)==s.charAt(half-i))) return s;
           //缩小一位
           half--;
       }

        //确定回文位置
        while (half>0){
            //half为中间
            for (i=0;i<half;i++){
                if (s.charAt(half+i)!=s.charAt(half-i)) break;
            }

            if (i==half&&s.charAt(half+i)==s.charAt(half-i)) {
                break;
            }

            //half不为中间，为右侧第一个
            for (i=0;i<half-1;i++){
                if (s.charAt(half+i)!=s.charAt(half-i-1)) break;
            }
            if (i==half-1&&s.charAt(half+i)==s.charAt(half-i-1)) {
                flag=0;
                break;
            }
            half--;
        }
        StringBuilder addString = new StringBuilder(s.substring(half+half+flag)).reverse();
        return addString.append(s).toString();
    }

    ////////////////////////////////////  官方题解一（字符串哈希）不建议  ////////////////////////////////////////////////
    public String shortestPalindrome1(String s) {
        int n = s.length();
        int base = 131, mod = 1000000007;
        int left = 0, right = 0, mul = 1;
        int best = -1;
        for (int i = 0; i < n; ++i) {
            left = (int) (((long) left * base + s.charAt(i)) % mod);
            right = (int) ((right + (long) mul * s.charAt(i)) % mod);
            if (left == right) {
                best = i;
            }
            mul = (int) ((long) mul * base % mod);
        }
        String add = (best == n - 1 ? "" : s.substring(best + 1));
        StringBuffer ans = new StringBuffer(add).reverse();
        ans.append(s);
        return ans.toString();
    }

    //////////////////////////////  官方题解二（KMP算法）  //////////////////////////////////
    public String shortestPalindrome2(String s) {
        int n = s.length();
        int[] fail = new int[n];
        Arrays.fill(fail, -1);
        for (int i = 1; i < n; ++i) {
            int j = fail[i - 1];
            while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                j = fail[j];
            }
            if (s.charAt(j + 1) == s.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        for (int j =0;j<s.length();j++){
            System.out.println(fail[j]);
        }

        int best = -1;
        for (int i = n - 1; i >= 0; --i) {
            while (best != -1 && s.charAt(best + 1) != s.charAt(i)) {
                best = fail[best];
            }
            if (s.charAt(best + 1) == s.charAt(i)) {
                ++best;
            }
        }
        System.out.println("-------------------");

        System.out.println(best);

        String add = (best == n - 1 ? "" : s.substring(best + 1));
        StringBuffer ans = new StringBuffer(add).reverse();
        ans.append(s);
        return ans.toString();
    }


    public String shortestPalindrome3(String s) {
        String ss = s + '#' + new StringBuilder(s).reverse();
        int max = getLastNext(ss);
        return new StringBuilder(s.substring(max)).reverse() + s;
    }

    //返回 next 数组的最后一个值
    public int getLastNext(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        int[] next = new int[n + 1];
        next[0] = -1;
        next[1] = 0;
        int k = 0;
        int i = 2;
        while (i <= n) {
            if (k == -1 || c[i - 1] == c[k]) {
                next[i] = k + 1;
                k++;
                i++;
            } else {
                k = next[k];
            }
        }
        for (int j =0;j<s.length();j++){
            System.out.println(next[j]);
        }
        return next[n];
    }

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        String aaccaaad = leetCode.shortestPalindrome2("aaccaaad");
        System.out.println(aaccaaad);
    }

}
