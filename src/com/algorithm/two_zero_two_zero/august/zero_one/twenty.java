package com.algorithm.two_zero_two_zero.august.zero_one;

import java.util.Stack;
import java.util.function.Function;

/**
   @author Ming
   @date 2020/8/1 - 20:08
   @describe
  */
public class twenty {

    /**
       运行时间：10ms

       占用内存：9308k
      */
    private static Stack<Integer> stack = new Stack<Integer>();
    private static Integer min = Integer.MAX_VALUE;

    public void push(int node) {
        if (stack.empty()||node<min){
            min=node;
        }
        stack.push(node);
    }

    public void pop() {
        if(stack.size() == 0) return;
        Integer pop = stack.pop();
        if(min.equals(pop)){
            if(stack.size() >=1){
                min=stack.peek();
                Stack<Integer> myStack = new Stack<Integer>();
                int size = stack.size();
                for (int i=0;i<size;i++){
                    if (min > stack.peek()) {
                        min = stack.peek();
                    }
                    myStack.push(stack.pop());
                }
                for (int i=0;i<size;i++){
                    stack.push(myStack.pop());
                }
            }else{
                min = Integer.MAX_VALUE;
            }
        }

    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min;
    }

    public static void main(String[] args) {
        twenty twenty = new twenty();
        twenty.push(3);
        System.out.println(twenty.min());
        twenty.push(4);
        System.out.println(twenty.min());
        twenty.push(2);
        System.out.println(twenty.min());
        twenty.push(3);
        System.out.println(twenty.min());
        twenty.pop();
        System.out.println(twenty.min());
        twenty.pop();
        System.out.println(  twenty.min());
        twenty.pop();
        System.out.println(twenty.min());;
        twenty.push(0);
        System.out.println(twenty.min());;


        Function<String, Integer> fun = Integer::parseInt;
    }
}
