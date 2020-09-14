package com.algorithm.september.zero_two;

/**
 * @author Ming
 * @date 2020/9/2 - 15:07
 * @describe 剑指 Offer 20. 表示数值的字符串
 */
public class LeetCode {

    /**
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
     * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
     */
    public boolean isNumber(String s) {
        if(s.endsWith("f")||s.endsWith("D")) return false;
        try {
            Double.valueOf(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
