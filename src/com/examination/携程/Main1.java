package com.examination.携程;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main1 {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int calc(String s, int index){
        int rs = 0;
        for (int i = index; i < s.length(); i++) {
            rs += s.charAt(i);
        }
        return rs;
    }
    static int dfs(String s1, String s2, int index1, int index2){
        if (s1.length() == index1 || s2.length() == index2) return calc(s1, index1) + calc(s2, index2);
        if (s1.charAt(index1) == s2.charAt(index2)) {
            return dfs(s1, s2, index1 + 1, index2 + 1);
        } else {
            return Math.min(dfs(s1, s2, index1 + 1, index2) + s1.charAt(index1),
                            dfs(s1, s2, index1, index2 + 1) + s2.charAt(index2));
        }
    }
    static int calcSimilarity(String name1, String name2) {
        String[] s1 = name1.split(" ");
        String[] s2 = name2.split(" ");
        int i = 0, rs = 0;
        while (i < s1.length && i < s2.length) {
            rs += dfs(s1[i], s2[i], 0, 0);
            i++;
        }
        if (i < s1.length) {
            while (i < s1.length) {
                rs += calc(s1[i], 0);
                i++;
            }
        }else {
            while (i < s2.length) {
                rs += calc(s2[i], 0);
                i++;
            }
        }
        return rs;
    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
//        System.out.println(calcSimilarity("Zhang San 1", "Zhan Ai"));
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String name1 = in.nextLine();
            String name2 = in.nextLine();
            int sum = calcSimilarity(name1, name2);
            System.out.println(sum);
        }
    }
}
