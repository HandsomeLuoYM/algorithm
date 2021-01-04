package com.algorithm.two_zero_two_zero.interview;

/**
 * @author Ming
 * @date 2020/9/15 - 20:17
 * @describe
 */
public class nine_one_five {
    static boolean foo(char c){
        System.out.println(c);
        return true;
    }
    public static void main(String[] args){
        int i =0;
        for (foo('B');foo('A')&&(i<2);foo('C')){
            i++;
            foo('D');
        }
    }
}
