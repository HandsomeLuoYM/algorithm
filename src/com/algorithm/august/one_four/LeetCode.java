package com.algorithm.august.one_four;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
   @author Ming
   @date 2020/8/14 - 11:19
   @describe
  */
public class LeetCode {

    /***
     *给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     */

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了79.37%的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了35.15%的用户
     */
    public boolean isValid(String s) {
        if (s==null) return false;
        Map<Character, Character> map = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};
        Stack stack = new Stack<Character>();
        int length = s.length();
        int i=0;
        while (i<length){
            char c = s.charAt(i);
            if (c=='('||c=='{'||c=='['){
                stack.push(map.get(c));
            }else {
                if (stack.isEmpty()||!stack.pop().equals(c)){
                    return false;
                }
            }
            i++;
        }
        return stack.isEmpty()?true:false;
    }

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        boolean valid = leetCode.isValid("()");
        System.out.println(valid);
    }
}
