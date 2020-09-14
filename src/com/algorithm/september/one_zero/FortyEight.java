package com.algorithm.september.one_zero;

/**
 * @author Ming
 * @date 2020/9/10 - 13:43
 * @describe
 */
public class FortyEight {

    /**
     *题目描述
     * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     */
    /**
     * 思路：想两数相异或，然后得到为有进位的数，然后在计算进位的值，
     *      当进位的值不为零时把两者当作两个加数，继续相加，当加数为0时停止。
     *
     * 运行时间：10ms
     *
     * 占用内存：9492k
     */
    public int Add(int num1,int num2) {
        int result = 0;
        int carry = 0;
        do{
            result = num1 ^ num2;       //不带进位的加法
            carry = (num1 & num2) << 1; //进位
            num1 = result;
            num2 = carry;
        }while(carry != 0); // 进位不为0则继续执行加法处理进位
        return result;
    }

}
