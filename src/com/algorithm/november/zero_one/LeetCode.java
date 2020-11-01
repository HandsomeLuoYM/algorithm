package com.algorithm.november.zero_one;

import java.util.*;

/**
 * @author Ming
 * @date 2020/11/1 - 12:37
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
     *
     * 说明：
     *      分隔时可以重复使用字典中的单词。
     *      你可以假设字典中没有重复的单词。
     *
     * 输入:
     * s = "catsanddog"
     * wordDict = ["cat", "cats", "and", "sand", "dog"]
     * 输出:
     * [
     *   "cats and dog",
     *   "cat sand dog"
     * ]
     *
     * 输入:
     * s = "pineapplepenapple"
     * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
     * 输出:
     * [
     *   "pine apple pen apple",
     *   "pineapple pen apple",
     *   "pine applepen apple"
     * ]
     *
     * 输入:
     * s = "catsandog"
     * wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出:
     * []
     */
    /**
     * 自己的做法：
     *      递归来判断单词，存在则添加，不存在则继续判断
     * 31 / 36 个通过测试用例
     */
    Set<String> wordSet = new HashSet<>();
    List<String> result = new ArrayList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        int length = wordDict.size();
        for (int i = 0; i<length; i++){
            wordSet.add(wordDict.get(i));
        }
        add( new StringBuilder(s),0);
        return result;
    }

    void add(StringBuilder s,int index){
        int length = s.length();
        if (wordSet.contains(s.substring(index,length))) result.add(s.toString());
        for (int i = index; i<length; i++){
            if (wordSet.contains(s.substring(index,i))){
                s.insert(i," ");
                add(s, i+1);
                s.deleteCharAt(i);
            }
        }
    }

    /**
     * 官方题解：记忆搜索
     * 执行用时：7 ms, 在所有 Java 提交中击败了84.37%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了30.07%的用户
     */
    class Solution {
        public List<String> wordBreak(String s, List<String> wordDict) {
            Map<Integer, List<List<String>>> map = new HashMap<Integer, List<List<String>>>();//存储记忆的map
            List<List<String>> wordBreaks = backtrack(s, s.length(), new HashSet<String>(wordDict), 0, map);//返回值
            List<String> breakList = new LinkedList<String>();//初始化答案
            for (List<String> wordBreak : wordBreaks) {//构造答案
                breakList.add(String.join(" ", wordBreak));
            }
            return breakList;//返回答案
        }

        /**
         *
         * @param s 字符串
         * @param length s的长度
         * @param wordSet set集，判断是否存在单词
         * @param index 当前到达的索引
         * @param map 记忆的map
         * @return 返回当前位置可以构造的字符串拆分
         */
        public List<List<String>> backtrack(String s, int length, Set<String> wordSet, int index, Map<Integer, List<List<String>>> map) {
            if (!map.containsKey(index)) {//记忆中不存在该位置后面所有情况
                List<List<String>> wordBreaks = new LinkedList<List<String>>();//构造这个索引后续可能存在的所有情况
                if (index == length) {//调整记忆集，防止后续都为空
                    wordBreaks.add(new LinkedList<String>());
                }
                for (int i = index + 1; i <= length; i++) {
                    String word = s.substring(index, i); //循环判断对否为单词
                    if (wordSet.contains(word)) {//为单词
                        List<List<String>> nextWordBreaks = backtrack(s, length, wordSet, i, map);//递归寻找后续单词
                        for (List<String> nextWordBreak : nextWordBreaks) {//添加单词，后续可以返回
                            LinkedList<String> wordBreak = new LinkedList<String>(nextWordBreak);
                            wordBreak.offerFirst(word);
                            wordBreaks.add(wordBreak);
                        }
                    }
                }
                map.put(index, wordBreaks);//修正记忆集
            }
            //存在则直接返回
            return map.get(index);
        }
    }


    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        Solution solution = leetCode.new Solution();
        List<String> list = solution.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
        System.out.println(list.toString());
    }

}
