package com.algorithm.two_zero_two_zero.july.one_six;

/**
   @author Ming
   @date 2020/7/16 - 12:50
   @describe
  */
public class twelve {
    /**
       给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。

       保证base和exponent不同时为0
      */


    /**
       运行时间：27ms
       <p>
       占用内存：10552k

       @param base
       @param exponent
       @return
      */
    private double Power(double base, int exponent) {
        return Math.pow(base, exponent);
    }

    /**
       网上方法，类似 暴力破解法
       运行时间：24ms
       <p>
       占用内存：10348k

       @param base
       @param exponent
       @return
      */
    private double Power1(double base, int exponent) {
        if (base == 0.0) {
            return 0.0;
        }
        // 前置结果设为1.0，即当exponent=0 的时候，就是这个结果
        double result = 1.0d;
        // 获取指数的绝对值
        int e = exponent > 0 ? exponent : -exponent;
        // 根据指数大小，循环累乘
        for (int i = 1; i <= e; i++) {
            result  = base;
        }
        // 根据指数正负，返回结果
        return exponent > 0 ? result : 1 / result;
    }


    /**
       运行时间：25ms
       <p>
       占用内存：10600k
       非递归的快速幂
       假设求x^6 ,已知6可以表示成二进制110
       可以表示成0 2^0 + 1 2^1 + 1 2^2 。

       @param b
       @param n
       @return
      */
    double Power2(double b, int n) {
        if (n < 0) {
            b = 1 / b;
            n = -n;
        }
        double x = b; // 记录x^0, x^1, x^2 ...
        double ret = 1.0;
        while (n != 0) {
            if ((n & 1) == 1) {
                ret  = x; // 二进制位数是1的，乘进答案。
            }
            x  = x;
            n >>= 1;
        }
        return ret;
    }


    /**
       运行时间：25ms
       <p>
       占用内存：10548k
       <p>
       假设我们求图片说明 ，如果我们知道图片说明 ，那么图片说明 ，所以图片说明
       <p>
       但是还有个小问题，如果n是偶数，那么上述没问题。
       代码如下：

       @param b
       @param n
       @return
      */
    double Power3(double b, int n) {
        if (n < 0) {
            b = 1 / b;
            n = -n;
        }
        return q_power(b, n);
    }

    double q_power(double b, int n) {
        if (n == 0) return 1.0;
        double ret = q_power(b, n / 2);
        if ((n & 1) == 1) { // 奇数
            return ret * ret * b;
        } else {
            return ret * ret;
        }
    }


}