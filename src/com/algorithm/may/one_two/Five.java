package com.algorithm.may.one_two;

import java.util.Stack;

/**
   @author Ming
   @date 2020/5/12 - 17:04
   @describe  用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

   将栈倒入到另一个栈中，实现栈的倒置，最后一个元素就是队头了
  */
public class Five {

    /**
       运行时间：13ms

       占用内存：9276k
      */
    public class Solution {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (stack2.size() <= 0) {
                while (stack1.size() != 0) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }

}
