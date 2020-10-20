package com.algorithm.october.one_four;

import java.util.*;

/**
 * @author Ming
 * @date 2020/10/14 - 9:35
 * @describe
 */
public class LeetCode {

    /**
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
     * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     *
     * 你可以按任意顺序返回答案。
     *
     * 示例 1：
     * 输入：["bella","label","roller"]
     * 输出：["e","l","l"]
     *
     * 示例 2：
     * 输入：["cool","lock","cook"]
     * 输出：["c","o"]
     *
     */
    /**
     * 自己的思路：
     * 用map记录字符和字符出现次数，在每次遍历中迭代更新
     *
     * 执行用时：23 ms, 在所有 Java 提交中击败了15.13%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了83.24%的用户
     */
    public List<String> commonChars(String[] A) {
        Map<Character,Integer> map = new HashMap<>(16);
        //初始化第一个元素
        for (int i=0; i<A[0].length(); i++){
            if (!map.containsKey(A[0].charAt(i))) {
                map.put(A[0].charAt(i),1);
            }else {
                map.put(A[0].charAt(i),map.get(A[0].charAt(i))+1);
            }
        }
        int length = A.length;
        Map<Character,Integer> nowMap;
        for (int i = 1; i<length; i++){
            nowMap = new HashMap<Character,Integer>();
            int sLength = A[i].length();
            for (int j = 0; j<sLength; j++){
                if (map.containsKey(A[i].charAt(j))){
                    if (!nowMap.containsKey(A[i].charAt(j))) {
                        nowMap.put(A[i].charAt(j),1);
                    }else if (nowMap.get(A[i].charAt(j))<map.get(A[i].charAt(j))){
                        nowMap.put(A[i].charAt(j),nowMap.get(A[i].charAt(j))+1);
                    }
                }
            }
            map = nowMap;
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<Character,Integer> entry : map.entrySet()){
            Integer num = entry.getValue();
            for (int i=0; i<num; i++){
                result.add(String.valueOf(entry.getKey()));
            }
        }
        return result;
    }

    /**
     * 官方题解
     */
    class Solution {
        public List<String> commonChars(String[] A) {
            int[] minfreq = new int[26];
            Arrays.fill(minfreq, Integer.MAX_VALUE);
            for (String word: A) {
                int[] freq = new int[26];
                int length = word.length();
                for (int i = 0; i < length; ++i) {
                    char ch = word.charAt(i);
                    ++freq[ch - 'a'];
                }
                for (int i = 0; i < 26; ++i) {
                    minfreq[i] = Math.min(minfreq[i], freq[i]);
                }
            }

            List<String> ans = new ArrayList<String>();
            for (int i = 0; i < 26; ++i) {
                for (int j = 0; j < minfreq[i]; ++j) {
                    ans.add(String.valueOf((char) (i + 'a')));
                }
            }
            return ans;
        }
    }

}
