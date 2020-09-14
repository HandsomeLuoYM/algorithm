package com.algorithm.august.two_four;

/**
 * @author Ming
 * @date 2020/8/24 - 11:49
 * @describe  459
 */
public class LeetCode {
    /**
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     */

    /**
     * 自己的解法
     *  执行用时：14 ms, 在所有 Java 提交中击败了83.80%的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了61.22%的用户
     */
    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        for (int i=1;i*2<length;i++){
            if (s.charAt(i)==s.charAt(0)&& length%i==0 ){
                int j = i , now = 0;
                for (;j<length;j++ , now++ ){
                    if(s.charAt(j)!=s.charAt(now)) break;
                }
                System.out.println(j);

                if (j==length) return true;
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern1(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }



    public static void main(String[] args) {
        LeetCode leetCode= new LeetCode();
        leetCode.repeatedSubstringPattern("abab");
    }
}
