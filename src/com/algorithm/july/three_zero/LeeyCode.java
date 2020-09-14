package com.algorithm.july.three_zero;

/**
   @author Ming
   @date 2020/7/30 - 15:08
   @describe
  */
public class LeeyCode {
    /**
       给定一个正整数 n，将其拆分为至少两个正整数的和，
       并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
      */
    public int integerBreak(int n) {
        if(n==2) return 1;
        if(n==3) return 2;
        int i = n/3;
        double pow = Math.pow(3, i - 1);
        int j = n%3;
        if (j==0){
            return ((int)pow)*3;
        }else if (j==1){
            return ((int)pow)*4;
        }else {
            return ((int)pow)*6;
        }
    }
}
