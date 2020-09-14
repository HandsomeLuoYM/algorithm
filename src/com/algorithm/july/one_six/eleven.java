package com.algorithm.july.one_six;

/**
 * @author Ming
 * @date 2020/7/16 - 11:30
 * @describe
 */
public class eleven {

    /**
     * 输入一个整数，输出该数32位二进制表示中1的个数。其中负数用补码表示。
     */

    /**
     * 运行时间：10ms
     * <p>
     * 占用内存：9544k
     *
     * @param n
     * @return
     */
    public int NumberOf1(int n) {
        String s = Integer.toBinaryString(n);
        String replace = s.replace("0", "");
        return replace.toString().length();
    }


    public static void main(String[] args) {
        eleven eleven = new eleven();
        eleven.NumberOf1(1);
    }
}
