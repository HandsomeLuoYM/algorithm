package com.algorithm.august.two_six;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ming
 * @date 2020/8/26 - 12:10
 * @describe 17
 */
public class LeetCode {

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */


    /**
     * 自己的解法（回溯）
     * 执行用时：7 ms, 在所有 Java 提交中击败了33.06%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了18.86%的用户
     */
    private char[][] nums = {{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};

    public List<String> letterCombinations(String digits) {
        if ("".equals(digits)) return new ArrayList<String>();
        List list = new ArrayList<String>();
        list.add("");
        return addTail(list,digits,0);
    }

    private List<String> addTail(List<String> list, String digits, int index){
        List newList = new ArrayList<String>();
        for (String s : list) {
            int ch = digits.charAt(index)-50;
            for (int i=0;i<nums[ch].length;i++){
                newList.add(s+nums[ch][i]);
            }
        }
        index++;
        return index==digits.length() ? newList:addTail(newList,digits,index);
    }

    ////////////////////////////////////////  官方题解一（回溯）  ////////////////////////////////////////////
    class Solution {
        public List<String> letterCombinations(String digits) {
            List<String> combinations = new ArrayList<String>();
            if (digits.length() == 0) {
                return combinations;
            }
            Map<Character, String> phoneMap = new HashMap<Character, String>() {{
                put('2', "abc");
                put('3', "def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7', "pqrs");
                put('8', "tuv");
                put('9', "wxyz");
            }};
            backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
            return combinations;
        }

        public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
            if (index == digits.length()) {
                combinations.add(combination.toString());
            } else {
                char digit = digits.charAt(index);
                String letters = phoneMap.get(digit);
                int lettersCount = letters.length();
                for (int i = 0; i < lettersCount; i++) {
                    combination.append(letters.charAt(i));
                    backtrack(combinations, phoneMap, digits, index + 1, combination);
                    combination.deleteCharAt(index);
                }
            }
        }
    }


}
