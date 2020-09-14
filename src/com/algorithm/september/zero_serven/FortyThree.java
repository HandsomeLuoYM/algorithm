package com.algorithm.september.zero_serven;

/**
 * @author Ming
 * @date 2020/9/7 - 15:43
 * @describe
 */
public class FortyThree {

    /**
     * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
     * 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
     * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
     */

    /**
     * 思路：计算移动的位数，然后通过字符串的拼接输出。
     * 运行时间：12ms
     *
     * 占用内存：9512k
     */
    public String LeftRotateString(String str,int n) {
        if (null==str||str.equals("")) return "";
        int length = str.length(),num=n%length;
        return str.substring(num,length)+str.substring(0,num);
    }
}
