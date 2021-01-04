package com.algorithm.two_zero_two_zero.september.one_six;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ming
 * @date 2020/9/16 - 22:01
 * @describe
 */
public class FiftyFour {

    /**
     * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
     * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
     * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
     *
     * 如果当前字符流没有存在出现一次的字符，返回#字符。
     */

    /**
     * 思路：必须使用LinkHashMap，因为HashMap是无序的
     * 运行时间：13ms
     *
     * 占用内存：9564k
     */
    Map<Character,Integer> map = new LinkedHashMap<>();

    public void Insert(char ch){
        if(map.containsKey(ch))
            map.put(ch,map.get(ch)+1);//value记录的是次数
        else
            map.put(ch,1);//第一次出现
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        for (Character ch : map.keySet()){
            if (map.get(ch)==1) return ch;
        }
        return '#';
    }

    /**
     * 单用数组来存储
     */
    public class Solution {
        int[] count = new int[128]; // 字符出现的次数
        int[] index = new int[128]; // 字符出现的位置
        int number = 0;//用于标记字符流的位置

        public void Insert(char ch) {
            count[ch]++;
            index[ch] = number++;
        }

        public char FirstAppearingOnce() {
            int minIndex = number;
            char ch = '#';
            for (int i = 0; i < 128; i++) {
                if (count[i] == 1 && index[i] < minIndex) {
                    ch = (char) i;
                    minIndex = index[i];
                }
            }
            return ch;
        }
    }
}
