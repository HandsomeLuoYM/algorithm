package com.algorithm.two_zero_two_one.march.逆波兰表达式求值;

import java.util.Stack;

/**
 * @author Ming
 * @date 2021/3/20 - 1:02
 * @describe
 */
public class LeetCode {
    /**
     * 根据 逆波兰表示法，求表达式的值。
     *
     * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     *
     *  
     *
     * 说明：
     *
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     *  
     *
     * 示例 1：
     *
     * 输入：tokens = ["2","1","+","3","*"]
     * 输出：9
     * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     * 示例 2：
     *
     * 输入：tokens = ["4","13","5","/","+"]
     * 输出：6
     * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
     * 示例 3：
     *
     * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     * 输出：22
     * 解释：
     * 该算式转化为常见的中缀算术表达式为：
     *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     *
     */
    /**
     * 栈
     */
    private static String a = "+";
    private static String b = "-";
    private static String c = "*";
    private static String d = "/";
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        int rs = 0;
        for (String token : tokens) {
            if (token.equals(a) || token.equals(b) || token.equals(c) || token.equals(d)) {
                stack.push(calc(token, stack.pop(), stack.pop()));
            }else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
    private int calc (String sign, int num1, int num2){
        switch (sign) {
            case "+" : return num1 + num2;
            case "-" : return num2 - num1;
            case "*" : return num1 * num2;
            case "/" : return num2 / num1;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        System.out.println(l.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }
}
