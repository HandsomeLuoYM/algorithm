package com.algorithm.august.zero_six;

import java.util.*;

/**
 * @author Ming
 * @date 2020/8/6 - 13:40
 * @describe
 */
public class LeetCode {
    /**
     * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，
     * 使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
     *
     * 输入: ["abcd","dcba","lls","s","sssll"]
     * 输出: [[0,1],[1,0],[3,2],[2,4]]
     * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
     *
     * 输入: ["bat","tab","cat"]
     * 输出: [[0,1],[1,0]]
     * 解释: 可拼接成的回文串为 ["battab","tabbat"]
     */

    /**
     * 暴力破解：超时
     */
    List lists = new ArrayList<List<Integer>>();

    private void judge(String str1,String str2,int i,int j){

        List<Integer> list;
        boolean flag = true;
        // 1 返回 str1 和str2 是；2 返回 str2 和str1 是；3 返回 str1 和str2 正反都是
        int result = 0;
        int length = (str1.length() + str2.length())/2;
        int allLength = str1.length() + str2.length();
        String str12 = str1+str2;

        for (int k=0;k<length;k++){
            if (str12.charAt(k)!=str12.charAt(allLength-k-1)){
                flag=false;
                break;
            }
        }
        if (flag){
            list = new ArrayList<>();
            list.add(i);
            list.add(j);
            lists.add(list);
            if (str1.length()==str2.length()){
                list = new ArrayList<>();
                list.add(j);
                list.add(i);
                lists.add(list);
                return;
            }
        }
        flag = true;
        String str21 = str2+str1;
        for (int k=0;k<length;k++){
            if (str21.charAt(k)!=str21.charAt(allLength-k-1)){
                flag=false;
                break;
            }
        }
        if (flag){
            list = new ArrayList<>();
            list.add(j);
            list.add(i);
            lists.add(list);
        }
    }
    public List<List<Integer>> palindromePairs(String[] words) {
        int length = words.length;
        for (int i=0;i<length;i++){
            for (int j = i+1 ;j<length ; j++){
                judge(words[i], words[j],i,j);
            }
        }
        return lists;
    }

    ///////////////////////////方法一：枚举前缀和后缀：字典树//////////////////////////////

    class Node {
        int[] ch = new int[26];
        int flag;

        public Node() {
            flag = -1;
        }
    }

    List<Node> tree = new ArrayList<Node>();

    public List<List<Integer>> palindromePairs1(String[] words) {
        tree.add(new Node());
        int n = words.length;
        for (int i = 0; i < n; i++) {
            insert(words[i], i);
        }
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            int m = words[i].length();
            for (int j = 0; j <= m; j++) {
                if (isPalindrome(words[i], j, m - 1)) {
                    int leftId = findWord(words[i], 0, j - 1);
                    if (leftId != -1 && leftId != i) {
                        ret.add(Arrays.asList(i, leftId));
                    }
                }
                if (j != 0 && isPalindrome(words[i], 0, j - 1)) {
                    int rightId = findWord(words[i], j, m - 1);
                    if (rightId != -1 && rightId != i) {
                        ret.add(Arrays.asList(rightId, i));
                    }
                }
            }
        }
        return ret;
    }

    public void insert(String s, int id) {
        int len = s.length(), add = 0;
        for (int i = 0; i < len; i++) {
            int x = s.charAt(i) - 'a';
            if (tree.get(add).ch[x] == 0) {
                tree.add(new Node());
                tree.get(add).ch[x] = tree.size() - 1;
            }
            add = tree.get(add).ch[x];
        }
        tree.get(add).flag = id;
    }

    public boolean isPalindrome(String s, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(left + i) != s.charAt(right - i)) {
                return false;
            }
        }
        return true;
    }

    public int findWord(String s, int left, int right) {
        int add = 0;
        for (int i = right; i >= left; i--) {
            int x = s.charAt(i) - 'a';
            if (tree.get(add).ch[x] == 0) {
                return -1;
            }
            add = tree.get(add).ch[x];
        }
        return tree.get(add).flag;
    }

    ///////////////////////////方法一：枚举前缀和后缀：哈希表//////////////////////////////

    List<String> wordsRev = new ArrayList<String>();
    Map<String, Integer> indices = new HashMap<String, Integer>();

    public List<List<Integer>> palindromePairs2(String[] words) {
        int n = words.length;
        for (String word: words) {
            wordsRev.add(new StringBuffer(word).reverse().toString());
        }
        for (int i = 0; i < n; ++i) {
            indices.put(wordsRev.get(i), i);
        }

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int m = words[i].length();
            if (m == 0) {
                continue;
            }
            for (int j = 0; j <= m; j++) {
                if (isPalindrome(word, j, m - 1)) {
                    int leftId = findWord2(word, 0, j - 1);
                    if (leftId != -1 && leftId != i) {
                        ret.add(Arrays.asList(i, leftId));
                    }
                }
                if (j != 0 && isPalindrome(word, 0, j - 1)) {
                    int rightId = findWord2(word, j, m - 1);
                    if (rightId != -1 && rightId != i) {
                        ret.add(Arrays.asList(rightId, i));
                    }
                }
            }
        }
        return ret;
    }

    public boolean isPalindrome2(String s, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(left + i) != s.charAt(right - i)) {
                return false;
            }
        }
        return true;
    }

    public int findWord2(String s, int left, int right) {
        return indices.getOrDefault(s.substring(left, right + 1), -1);
    }



}
