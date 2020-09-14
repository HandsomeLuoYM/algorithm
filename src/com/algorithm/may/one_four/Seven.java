package com.algorithm.may.one_four;


/**
   @author Ming
   @date 2020/5/15 - 0:45
   @describe
   大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0，第1项是1）。
   n<=39
  */
public class Seven {

    /**
       运行时间：13ms

       占用内存：9304k
       @param n
       @return
       自己的方法
      */
    public int Fibonacci(int n) {
        int array[] = new int[n];
        int i;
        array[0] = 0;
        array[1] = 1;
        for (i = 2; i<=n ; i++){
            array[i] = array[i-2]+array[i-1];
        }
        return array[n];
    }


    /**
       运行时间：1123ms

       占用内存：9496k
       @param n
       @return
       递归
      */
    public int Fibonacci1(int n) {
        if(n<=1){
            return n;
        }
        return Fibonacci(n-1) + Fibonacci(n-2);
    }


    public static void main(String[] args) {
        Seven seven = new Seven();
        System.out.println(seven.Fibonacci(2));
    }

}
