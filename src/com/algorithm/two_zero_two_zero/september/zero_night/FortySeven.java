package com.algorithm.two_zero_two_zero.september.zero_night;

/**
 * @author Ming
 * @date 2020/9/9 - 17:18
 * @describe
 */
public class FortySeven {

    /**
     * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     */

    /**
     * 自己的解法
     *
     * 思路：递归求其相加
     *
     * 运行时间：11ms
     *
     * 占用内存：9264k
     */
    public int Sum_Solution(int n) {
        if (n==0) return 0;
        return n+Sum_Solution(n-1);
    }

    /**
     * 短路与来停止递归
     */
    int Sum_Solution1(int n) {
        int sum = n;
        boolean ans = (n>0)&&((sum+=Sum_Solution1(n-1))>0);
        return sum;
    }

}
