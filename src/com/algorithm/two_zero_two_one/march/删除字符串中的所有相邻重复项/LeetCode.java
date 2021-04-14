package com.algorithm.two_zero_two_one.march.删除字符串中的所有相邻重复项;

import java.util.Stack;

/**
 * @author Ming
 * @date 2021/3/9 - 0:21
 * @describe
 */
public class LeetCode {
    /**
     * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
     *
     * 在 S 上反复执行重复项删除操作，直到无法继续删除。
     *
     * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
     *
     * 示例：
     *
     * 输入："abbaca"
     * 输出："ca"
     * 解释：
     * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
     *
     */
    /**
     * 思路：栈
     * 执行用时：212 ms, 在所有 Java 提交中击败了10.68%的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了5.02%的用户
     */
    public String removeDuplicates(String S) {
        if (S== null || S.length() == 0) return S;
        int index = 0, length = S.length();
        Stack<Character> stack = new Stack<Character>();
        stack.push(S.charAt(0));
        for (int i = 1; i < length; i++) {
            if (!stack.isEmpty() && stack.peek().equals(S.charAt(i))) {
                stack.pop();
            }else {
                stack.push(S.charAt(i));
            }
        }
        String string = "";
        while (!stack.isEmpty()) {
            string = stack.pop() + string;
        }
        return string.toString();
    }
}
