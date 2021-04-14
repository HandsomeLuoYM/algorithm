package com.algorithm.two_zero_two_one.march.基本计算器;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Ming
 * @date 2021/3/10 - 10:31
 * @describe
 */
public class LeetCode {
    /**
     * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
     *
     * 示例 1：
     *
     * 输入：s = "1 + 1"
     * 输出：2
     * 示例 2：
     *
     * 输入：s = " 2-1 + 2 "
     * 输出：3
     * 示例 3：
     *
     * 输入：s = "(1+(4+5+2)-3)+(6+8)"
     * 输出：23
     *
     */
    /**
     * 思路：模拟
     * 执行用时：6 ms, 在所有 Java 提交中击败了94.82%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了51.49%的用户
     */
    int i = 0;
    public int calculate(String s) {
        int rs = 0;
        char ch = '+', now;
        for (; i < s.length(); i++) {
            now = s.charAt(i);
            //空格直接忽略
            if (now == ' ') continue;
                //左括号直接递归
            else if (now == '(') {
                rs = calc(ch, rs, dfs(s, ++i));
                //右括号递归返回
            }else if (now == ')') {
                rs = calc('+', rs, dfs(s, ++i));
            }else if (now == '+' || now == '-') {
                ch = now;
            }else {
                int num2 = 0;
                while (i < s.length() && (s.charAt(i) <= '9' && s.charAt(i) >= '0')){
                    num2 = num2 * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                rs = calc(ch, rs, num2);
                if (i == s.length() - 1) return rs;
            }
        }
        return rs;
    }
    private int dfs(String s, int index) {
        int rs = 0;
        char ch = '+', now;
        for (; i < s.length(); i++) {
            now = s.charAt(i);
            //空格直接忽略
            if (now == ' ') continue;
            //左括号直接递归
            else if (now == '(') {
                rs = calc(ch, rs, dfs(s, ++i));
             //右括号递归返回
            }else if (now == ')') {
                return rs;
            }else if (now == '+' || now == '-') {
                ch = now;
            }else {
                int num2 = 0;
                while (i < s.length() && (s.charAt(i) <= '9' && s.charAt(i) >= '0')){
                    num2 = num2 * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                rs = calc(ch, rs, num2);
                if (i == s.length() - 1) return rs;
            }
        }
        return rs;
    }

    private int calc(char ch, int num1, int num2){
        switch (ch) {
            case '+' :
                return num1 + num2;
            case '-' :
                return num1 - num2;
            default: return 0;
        }
    }

    /**
     * 括号展开 + 栈
     */
    static class Solution {
        public int calculate(String s) {
            Deque<Integer> ops = new LinkedList<Integer>();
            ops.push(1);
            int sign = 1;

            int ret = 0;
            int n = s.length();
            int i = 0;
            while (i < n) {
                if (s.charAt(i) == ' ') {
                    i++;
                } else if (s.charAt(i) == '+') {
                    sign = ops.peek();
                    i++;
                } else if (s.charAt(i) == '-') {
                    sign = -ops.peek();
                    i++;
                } else if (s.charAt(i) == '(') {
                    ops.push(sign);
                    i++;
                } else if (s.charAt(i) == ')') {
                    ops.pop();
                    i++;
                } else {
                    long num = 0;
                    while (i < n && Character.isDigit(s.charAt(i))) {
                        num = num * 10 + s.charAt(i) - '0';
                        i++;
                    }
                    ret += sign * num;
                }
            }
            return ret;
        }

    }

}
