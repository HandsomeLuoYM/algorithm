package com.algorithm.two_zero_two_zero.may.one_five;

/**
   @author Ming
   @date 2020/5/15 - 10:09
   @describe 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
   可用贪心算法
 * */
public class Nine {

    /**
       运行时间：21ms
       <p>
       占用内存：9396k

       @param target
       @return 自己的解法（递归）
      */
    public int JumpFloorII(int target) {
        if (target == 0) return 1;
        int number = 0;
        for (int i = 1; i <= target; i++) {
            number = number + JumpFloorII(target - i);
        }
        return number;
    }


}
