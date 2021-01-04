package com.algorithm.two_zero_two_zero.august.zero_two;

import java.util.Stack;

/**
   @author Ming
   @date 2020/8/2 - 0:09
   @describe
 */
public class twety_one {

    /**

     输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
     假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
     但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
      */

    /**
       运行时间：12ms

       占用内存：9216k
       @param pushA
       @param popA
       @return
      */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack stack = new Stack<Integer>();
        int pushLength = pushA.length;
        int popLength = popA.length;
        int i=0,j=0;
        while (i<pushLength){
            if (stack.isEmpty()||!stack.peek().equals(popA[j])){
                stack.push(pushA[i++]);
            }else {
                stack.pop();
                j++;
            }
        }
        while (!stack.isEmpty()){
            if (stack.peek().equals(popA[j])){
                j++;
                continue;
            }
            break;
        }
        return j==i?true:false;
    }


}
