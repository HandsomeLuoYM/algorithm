package com.algorithm.september.one_five;

import java.util.regex.Pattern;

/**
 * @author Ming
 * @date 2020/9/15 - 12:05
 * @describe
 */
public class FiftyThree {

    /**
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是
     */
    /**
     * 异常骚操作
     * 运行时间：13ms
     *
     * 占用内存：9636k
     */
    public boolean isNumeric(char[] str) {
        try {
            String s = String.valueOf(str);
            Double aDouble = Double.valueOf(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 正则匹配
     *
     * 运行时间：14ms
     *
     * 占用内存：9516k
     */
    public static boolean isNumeric1(char[] str) {
        String pattern = "^[-+]?\\d*(?:\\.\\d*)?(?:[eE][+\\-]?\\d+)?$";
        return Pattern.matches(pattern,new String(str));
    }


}
