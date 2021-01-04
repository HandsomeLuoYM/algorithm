package com.algorithm.two_zero_two_zero.september.zero_two;

/**
 * @author Ming
 * @date 2020/9/2 - 15:17
 * @describe
 */
public class ThirtyOne {

    /**
     * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
     * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,
     * 但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,
     * 可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
     */

    /**
     * 自己的做法
     * 运行时间：12ms
     *
     * 占用内存：9440k
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int j,k,all=0;
        for (int i=1;i<=n;i++){
            j=i;
            while (j!=0){
                k=j%10;
                j=j/10;
                if (k==1) all++;
            }
        }
        return all;
    }



}
