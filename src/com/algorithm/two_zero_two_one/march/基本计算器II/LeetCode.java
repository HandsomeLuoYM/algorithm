package com.algorithm.two_zero_two_one.march.基本计算器II;

import javax.xml.stream.events.Characters;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Ming
 * @date 2021/3/11 - 0:13
 * @describe
 */
public class LeetCode {
    /**
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     *
     * 整数除法仅保留整数部分。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "3+2*2"
     * 输出：7
     * 示例 2：
     *
     * 输入：s = " 3/2 "
     * 输出：1
     * 示例 3：
     *
     * 输入：s = " 3+5 / 2 "
     * 输出：5
     *
     */
    /**
     * 思路：两次遍历，模拟
     * 执行用时：14 ms, 在所有 Java 提交中击败了57.63%的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了34.16%的用户
     */
    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>(), signStack = new Stack<>();
        int length = s.length(), i = 0; ;
        char ch;
        signStack.push(1);
        while (i < length) {
            ch = s.charAt(i);
            if (ch == ' ') i++;
            else if (ch == '*' || ch == '/') {
                int num = 0;
                i++;
                while (i < length && s.charAt(i) == ' ') i++;
                while (i < length && s.charAt(i) <= '9' && s.charAt(i) >='0') {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                numStack.push(calc(ch, numStack.pop(), num));
            }else if (ch == '+'){
                signStack.push(1);
                i++;
            }else if (ch == '-'){
                signStack.push(-1);
                i++;
            }else {
                int num = 0;
                while (i < length && s.charAt(i) <= '9' && s.charAt(i) >='0') {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                numStack.push(num);
            }
        }
        int rs = 0;
        while (!signStack.isEmpty() && !numStack.isEmpty()) {
            rs += signStack.pop() * numStack.pop();
        }
        return rs;
    }
    private int calc(char sign, int num1, int num2){
        switch (sign) {
            case '+' : return num1 + num2;
            case '-' : return num1 - num2;
            case '*' : return num1 * num2;
            case '/' : return num1 / num2;
            default: return 0;
        }
    }

    class Solution {
        public int calculate(String s) {
            Deque<Integer> stack = new LinkedList<Integer>();
            char preSign = '+';
            int num = 0;
            int n = s.length();
            for (int i = 0; i < n; ++i) {
                if (Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                }
                if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                    switch (preSign) {
                        case '+':
                            stack.push(num);
                            break;
                        case '-':
                            stack.push(-num);
                            break;
                        case '*':
                            stack.push(stack.pop() * num);
                            break;
                        default:
                            stack.push(stack.pop() / num);
                    }
                    preSign = s.charAt(i);
                    num = 0;
                }
            }
            int ans = 0;
            while (!stack.isEmpty()) {
                ans += stack.pop();
            }
            return ans;
        }
    }


}
