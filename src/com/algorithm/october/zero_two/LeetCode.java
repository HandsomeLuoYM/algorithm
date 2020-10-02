package com.algorithm.october.zero_two;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Ming
 * @date 2020/10/2 - 19:56
 * @describe 771 宝石与石头
 */
public class LeetCode {

    /**
     * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 
     * S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     *
     * J 中的字母不重复，J 和 S中的所有字符都是字母。
     * 字母区分大小写，因此"a"和"A"是不同类型的石头。
     *
     * 输入: J = "aA", S = "aAAbbbb"
     * 输出: 3
     *
     * 输入: J = "z", S = "ZZ"
     * 输出: 0
     */
    /**
     * 自己的思路：hash存放宝石信息，遍历石头判断
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.67%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了48.69%的用户
     */
    public int numJewelsInStones(String J, String S) {
        if (J==null||S==null) return 0;
        Set<Character> set = new HashSet<>();
        int jLength = J.length(),sLength = S.length();
        for (int i = 0 ; i<jLength ; i++){
            set.add(J.charAt(i));
        }
        int all = 0;
        for (int i = 0;i<sLength;i++){
            if (set.contains(S.charAt(i))) all++;
        }
        return all;
    }
}
