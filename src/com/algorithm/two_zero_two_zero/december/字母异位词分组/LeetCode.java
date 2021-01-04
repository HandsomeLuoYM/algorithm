package com.algorithm.two_zero_two_zero.december.字母异位词分组;

import java.util.*;

/**
 * @author Ming
 * @date 2020/12/14 - 1:17
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     *
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     *
     */
    /**
     * 自己的做法：
     *      和题解二一样，统计个数用hash存储（计数存储）
     * 执行用时：22 ms, 在所有 Java 提交中击败了21.42%的用户
     * 内存消耗：43 MB, 在所有 Java 提交中击败了5.53%的用户
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            int length = str.length();
            int[] nums = new int[26];
            for (int i = 0; i<length; i++){
                nums[str.charAt(i)-'a']++;
            }
            String key = Arrays.toString(nums);
            if (map.containsKey(key)){
                map.get(key).add(str);
            }else {
                map.put(key,new ArrayList<String>(){{add(str);}});
            }
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     * 排序存储
     */
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<String, List<String>>();
            for (String str : strs) {
                char[] array = str.toCharArray();
                Arrays.sort(array);
                String key = new String(array);
                List<String> list = map.getOrDefault(key, new ArrayList<String>());
                list.add(str);
                map.put(key, list);
            }
            return new ArrayList<List<String>>(map.values());
        }
    }

}
