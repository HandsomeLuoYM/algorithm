package com.algorithm.two_zero_two_zero.november.three_zero;

import java.util.PriorityQueue;

/**
 * @author Ming
 * @date 2020/11/30 - 8:53
 * @describe 767. 重构字符串
 */
public class LeetCode {
    /**
     * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
     * 若可行，输出任意可行的结果。若不可行，返回空字符串。
     *
     * 输入: S = "aab"
     * 输出: "aba"
     *
     * 输入: S = "aaab"
     * 输出: ""
     */
    /**
     * 自己的思路：
     *      先记录所有的字符出现的次数，然后再重新构造一个新的字符，再构造时需要初始化，再插入构造
     * 执行用时：3 ms, 在所有 Java 提交中击败了48.41%的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了56.88%的用户
     */
    public String reorganizeString(String S) {
        int length = S.length();

        int[] charNumber = new int[26];
        for (int i = 0; i<length; i++){
            charNumber[S.charAt(i)-97]++;
        }
        //排除掉不满足情况
        int half = (1+length)/2, max = 0;
        for (int i = 0; i<26; i++){
            if (charNumber[i]>half) return "";
            //找到最多字符的众数
            if (charNumber[i]>charNumber[max]) max = i;
        }
        //重构字符串
        char[] result = new char[length];
        //初步重构
        int maxNumber = charNumber[max];
        for (int j = 0; j<maxNumber; j++){
            result[2*j] = (char)(max+97);
        }
        charNumber[max] = 0;
        int i = 0;
        while (i<maxNumber-1){
            int j = 0;
            while (charNumber[j] == 0) j++;
            while (charNumber[j]>0  && i<maxNumber-1){
                result[2*i+1] = (char)(j+97);
                charNumber[j]--;
                i++;
            }
        }
        //后续构造
        i=25;
        while (i >= 0){
            if (charNumber[i]==0) {
                i--;
                continue;
            }
            int now = charNumber[i];
            while (now>0){
                for (int j = 0; j<length-1; j++){
                    //插入到头
                    if (result[0]!=(i+97)) {
                        remove(result, 0, (char) (i+97));
                        break;
                    }
                    //插入到字符中间
                    if (result[j]!=(i+97) && result[j+1]!=(i+97)) {
                        remove(result, j+1, (char) (i+97));
                        break;
                    }
                }
                now--;
            }
            charNumber[i]=0;
            i--;
        }
        return new String(result);
    }
    void remove(char[] chars, int index, char ch){
        int now = index;
        char old,temp = ch;
        while (index < chars.length-1 && chars[index]!=0){
            old = chars[index];
            chars[index] = temp;
            temp = old;
            index++;
        }
        chars[index] = temp;
    }

    /**
     * 统计完构造。左右两边一起构建
     */
    class Solution {
        public String reorganizeString(String S) {
            if (S.length() < 2) {
                return S;
            }
            int[] counts = new int[26];
            int maxCount = 0;
            int length = S.length();
            for (int i = 0; i < length; i++) {
                char c = S.charAt(i);
                counts[c - 'a']++;
                maxCount = Math.max(maxCount, counts[c - 'a']);
            }
            if (maxCount > (length + 1) / 2) {
                return "";
            }
            PriorityQueue<Character> queue = new PriorityQueue<Character>(( letter1, letter2)-> {
                    return counts[letter2 - 'a'] - counts[letter1 - 'a'];
                });
            for (char c = 'a'; c <= 'z'; c++) {
                if (counts[c - 'a'] > 0) {
                    queue.offer(c);
                }
            }
            StringBuffer sb = new StringBuffer();
            while (queue.size() > 1) {
                char letter1 = queue.poll();
                char letter2 = queue.poll();
                sb.append(letter1);
                sb.append(letter2);
                int index1 = letter1 - 'a', index2 = letter2 - 'a';
                counts[index1]--;
                counts[index2]--;
                if (counts[index1] > 0) {
                    queue.offer(letter1);
                }
                if (counts[index2] > 0) {
                    queue.offer(letter2);
                }
            }
            if (queue.size() > 0) {
                sb.append(queue.poll());
            }
            return sb.toString();
        }
    }

    class Solution1 {
        public String reorganizeString(String S) {
            if (S.length() < 2) {
                return S;
            }
            int[] counts = new int[26];
            int maxCount = 0;
            int length = S.length();
            for (int i = 0; i < length; i++) {
                char c = S.charAt(i);
                counts[c - 'a']++;
                maxCount = Math.max(maxCount, counts[c - 'a']);
            }
            if (maxCount > (length + 1) / 2) {
                return "";
            }
            char[] reorganizeArray = new char[length];
            int evenIndex = 0, oddIndex = 1;
            int halfLength = length / 2;
            for (int i = 0; i < 26; i++) {
                char c = (char) ('a' + i);
                while (counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                    reorganizeArray[oddIndex] = c;
                    counts[i]--;
                    oddIndex += 2;
                }
                while (counts[i] > 0) {
                    reorganizeArray[evenIndex] = c;
                    counts[i]--;
                    evenIndex += 2;
                }
            }
            return new String(reorganizeArray);
        }
    }

}
