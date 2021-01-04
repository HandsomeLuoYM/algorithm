package com.algorithm.two_zero_two_zero.august.one_zero;

import java.util.ArrayList;
import java.util.List;

/**
   @author Ming
   @date 2020/8/10 - 15:55
   @describe
  */
public class LeetCode {

    /**
       给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
       重复出现的子串要计算它们出现的次数。

       输入: "00110011"
       输出: 6
       解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。

       请注意，一些重复出现的子串要计算它们出现的次数。

       另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。

      */
    /**
       执行用时：13 ms, 在所有 Java 提交中击败了72.77%的用户
       内存消耗：39.8 MB, 在所有 Java 提交中击败了99.41%的用户
      */
    public int countBinarySubstrings(String s) {
        int number = 0,length = s.length();
        int[] flag = new int[2];
        char last = s.charAt(0);
        flag[s.charAt(0)-48]++;
        for (int i = 1;i<length;i++){
            if (last==s.charAt(i)){
                flag[s.charAt(i)-48]++;
            }else {
                number += Math.min(flag[0],flag[1]);
                last=s.charAt(i);
                if (s.charAt(i)=='0') flag[0]=1;
                else flag[1] = 1 ;
            }
        }
        number += Math.min(flag[0],flag[1]);
        return number;
    }


    /**
       我们可以将字符串s按照0和1的连续段分组，存在counts数组中，
       例如s=0011011，可以得到这样的counts 数组∶counts ={2，3，1，2}。

       这里counts数组中两个相令邻的数一定代表的是两种不同的字符。
       假设 counts数组中两个相的数字为u或者v，它们对应着u个0和v个1，
       或者u个1和v个0。它们能组成的满足条件的子串数目为min{u，}，
       即一对相邻的数字对答案的贡献。
      */
    public int countBinarySubstrings1(String s) {
        List<Integer> counts = new ArrayList<Integer>();
        int ptr = 0, n = s.length();
        while (ptr < n) {
            char c = s.charAt(ptr);
            int count = 0;
            while (ptr < n && s.charAt(ptr) == c) {
                ++ptr;
                ++count;
            }
            counts.add(count);
        }
        int ans = 0;
        for (int i = 1; i < counts.size(); ++i) {
            ans += Math.min(counts.get(i), counts.get(i - 1));
        }
        return ans;
    }

}
