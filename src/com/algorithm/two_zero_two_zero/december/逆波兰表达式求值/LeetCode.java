package com.algorithm.two_zero_two_zero.december.逆波兰表达式求值;

import java.util.Stack;

/**
 * @author Ming
 * @date 2020/12/29 - 0:05
 * @describe
 */
public class LeetCode {
    /**
     * 根据 逆波兰表示法，求表达式的值。
     *
     * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     *
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     *
     * 输入: ["2", "1", "+", "3", "*"]
     * 输出: 9
     * 解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     *
     * 输入: ["4", "13", "5", "/", "+"]
     * 输出: 6
     * 解释: 该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
     *
     * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
     * 输出: 22
     * 解释:
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
     * 自己的思路：
     *      利用栈来存储数字，在遇到符号时进行计算，再将结果入栈
     * 执行用时：6 ms, 在所有 Java 提交中击败了87.01%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了90.90%的用户
     */
    public int evalRPN(String[] tokens) {
        int length = tokens.length, num1, num2;
        Stack<String> number = new Stack<String>();
        for (int i = 0; i < length; i++){
            switch (tokens[i]){
                case "+":
                    number.push(String.valueOf(Integer.parseInt(number.pop()) + Integer.parseInt(number.pop())));
                    break;
                case "-":
                    num1 = Integer.parseInt(number.pop());
                    num2 = Integer.parseInt(number.pop());
                    number.push(String.valueOf(num2 - num1));
                    break;
                case "*":
                    number.push(String.valueOf(Integer.parseInt(number.pop()) * Integer.parseInt(number.pop())));
                    break;
                case "/":
                    num1 = Integer.parseInt(number.pop());
                    num2 = Integer.parseInt(number.pop());
                    number.push(String.valueOf(num2 / num1));
                    break;
                default: number.push(tokens[i]);
            }
        }
        return Integer.parseInt(number.pop());
    }

}
