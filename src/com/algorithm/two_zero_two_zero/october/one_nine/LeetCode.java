package com.algorithm.two_zero_two_zero.october.one_nine;

import java.util.Stack;

/**
 * @author Ming
 * @date 2020/10/19 - 0:39
 * @describe
 */
public class LeetCode {

    /**
     * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
     *
     * 注意：如果对空文本输入退格字符，文本继续为空。
     *
     * 输入：S = "ab#c", T = "ad#c"
     * 输出：true
     * 解释：S 和 T 都会变成 “ac”。
     *
     * 输入：S = "ab##", T = "c#d#"
     * 输出：true
     * 解释：S 和 T 都会变成 “”。
     */

    /**
     * 看了题解思路后的实现
     *      ：由于#是忽略前面一个，所以很难从前往后遍历，就从后往前遍历，遇到 # 就跳到下一个
     * 执行用时：1 ms, 在所有 Java 提交中击败了96.45%的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean backspaceCompare(String S, String T) {
        int sIndex = S.length()-1, tIndex = T.length()-1,num;
        while (sIndex>=0 || tIndex>=0){
            while (sIndex>=0 && S.charAt(sIndex) == '#'){
                num = 1;
                sIndex--;
                while (num>0) {
                    if (sIndex<0) break;
                    if(S.charAt(sIndex) == '#') num++;
                    else num--;
                    sIndex--;
                }
            }
            while (tIndex>=0 && T.charAt(tIndex) == '#'){
                num = 1;
                tIndex--;
                while (num>0) {
                    if (tIndex<0) break;
                    if(T.charAt(tIndex) == '#') num++;
                    else num--;
                    tIndex--;
                }
            }
            if (sIndex<0 && tIndex<0) return true;
            if ((sIndex<0 && tIndex>=0) || (sIndex>=0 && tIndex<0)) return false;
            if (S.charAt(sIndex) != T.charAt(tIndex)) {
                return false;
            }
            sIndex--;
            tIndex--;
        }
        return sIndex<0&&tIndex<0;
    }

    /**
     * 自己的思路：
     *      通过栈从前往后构造新的字符串，再判断是否相等
     * 执行用时：2 ms, 在所有 Java 提交中击败了74.82%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean backspaceCompare1(String S, String T) {
        Stack<Character> sStack = new Stack() , tStack = new Stack();
        int sLength = S.length(), tLength = T.length();
        for (int i = 0; i<sLength; i++){
            if (S.charAt(i) != '#') sStack.push(S.charAt(i));
            else{
                if (!sStack.isEmpty()) sStack.pop();
            }
        }
        for (int i = 0; i<tLength; i++){
            if (T.charAt(i) != '#') tStack.push(T.charAt(i));
            else{
                if (!tStack.isEmpty()) tStack.pop();
            }
        }
        if (sStack.size() != tStack.size()) return false;
        int length = sStack.size();
        for (int i = 0; i<length; i++){
            if (!sStack.pop().equals(tStack.pop())) return false;
        }
        return true;
    }
}
