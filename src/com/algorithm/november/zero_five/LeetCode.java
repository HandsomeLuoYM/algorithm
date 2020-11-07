package com.algorithm.november.zero_five;

import java.util.*;

/**
 * @author Ming
 * @date 2020/11/5 - 15:28
 * @describe 127. 单词接龙
 */
public class LeetCode {
    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
     *
     * 每次转换只能改变一个字母。
     * 转换过程中的中间单词必须是字典中的单词。
     * 说明:
     *
     * 如果不存在这样的转换序列，返回 0。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     *
     * 输入:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * 输出: 5
     * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     *      返回它的长度 5。
     *
     *
     * 输入:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     * 输出: 0
     * 解释: endWord "cog" 不在字典中，所以无法进行转换。
     *
     */
    /**
     * 我的思路：
     *      先确定每个单词可以转换的其他单词，然后进行深度搜索，同时回溯
     * 超时TTL
     */
    boolean judge(String str1, String str2, int length){
        int differenceNumber = 0;
        for (int i = 0; i<length; i++){
            if (str1.charAt(i) != str2.charAt(i)) differenceNumber++;
            if (differenceNumber > 1) return false;
        }
        return differenceNumber == 1;
    }
    int shortNumber = Integer.MAX_VALUE;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0) {
            return 0;
        }
        int length = wordList.size(), wordLength = wordList.get(0).length();
        Map<String,List<String>> map = new HashMap<>();
        Set<String> path = new HashSet<>();
        List<String> beginList = new LinkedList<>();
        boolean flag = false;
        for (int i = 0; i<length; i++){
            map.put(wordList.get(i),new LinkedList<String>());
            if (judge(wordList.get(i),beginWord,wordLength)) beginList.add(wordList.get(i));
            if (wordList.get(i).equals(endWord)) flag = true;
        }
        if (!flag) return 0;
        for (int i = 0; i<length; i++){
            for (int j = i+1; j<length; j++){
                if (judge(wordList.get(i),wordList.get(j),wordLength)) {
                    map.get(wordList.get(i)).add(wordList.get(j));
                    map.get(wordList.get(j)).add(wordList.get(i));
                }
            }
        }
        int nowNumber = 2;
        for (int i = 0; i<beginList.size(); i++){
            path.add(beginList.get(i));
            dfs(path,map,endWord,nowNumber,beginList.get(i));
            path.remove(beginList.get(i));
        }
        return shortNumber == Integer.MAX_VALUE ? 0 : shortNumber;
    }

    private void dfs(Set<String> path, Map<String, List<String>> map, String endWord, int nowNumber, String lastWord) {
        if (endWord.equals(lastWord)){
            shortNumber = Math.min(shortNumber,nowNumber);
            return;
        }
        List<String> list = map.get(lastWord);
        for (int i = 0; i<list.size(); i++){
            if (!path.contains(list.get(i))){
                path.add(list.get(i));
                dfs(path,map,endWord,nowNumber+1, list.get(i));
                path.remove(list.get(i));
            }
        }
    }

    class Solution {
        Map<String, Integer> wordId = new HashMap<String, Integer>();
        List<List<Integer>> edge = new ArrayList<List<Integer>>();
        int nodeNum = 0;

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            for (String word : wordList) {
                addEdge(word);
            }
            addEdge(beginWord);
            if (!wordId.containsKey(endWord)) {
                return 0;
            }
            int[] dis = new int[nodeNum];
            Arrays.fill(dis, Integer.MAX_VALUE);
            int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
            dis[beginId] = 0;

            Queue<Integer> que = new LinkedList<Integer>();
            que.offer(beginId);
            while (!que.isEmpty()) {
                int x = que.poll();
                if (x == endId) {
                    return dis[endId] / 2 + 1;
                }
                for (int it : edge.get(x)) {
                    if (dis[it] == Integer.MAX_VALUE) {
                        dis[it] = dis[x] + 1;
                        que.offer(it);
                    }
                }
            }
            return 0;
        }

        public void addEdge(String word) {
            addWord(word);
            int id1 = wordId.get(word);
            char[] array = word.toCharArray();
            int length = array.length;
            for (int i = 0; i < length; ++i) {
                char tmp = array[i];
                array[i] = '*';
                String newWord = new String(array);
                addWord(newWord);
                int id2 = wordId.get(newWord);
                edge.get(id1).add(id2);
                edge.get(id2).add(id1);
                array[i] = tmp;
            }
        }

        public void addWord(String word) {
            if (!wordId.containsKey(word)) {
                wordId.put(word, nodeNum++);
                edge.add(new ArrayList<Integer>());
            }
        }
    }

}
