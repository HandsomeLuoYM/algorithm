package com.algorithm.two_zero_two_zero.september.zero_three;

/**
 * @author Ming
 * @date 2020/9/3 - 16:58
 * @describe
 */
public class ThirtyFour {

    /**
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
     * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
     */

    /**
     * 自己的解法：设置两个数组一个记录个数，一个记录最后一次出现的次数
     * 最后遍历数组，查找只出现一次的字符，然后查找相对应的出现表，如果只有一次那么它出现的第一次也就是最后一次
     *
     * 运行时间：16ms
     *
     * 占用内存：9516k
     *
     */
    public int FirstNotRepeatingChar(String str) {
        int[] asc = new int[128];
        int[] first = new int[128];
        int length = str.length();
        for (int i=0;i<length;i++){
            asc[str.charAt(i)]++;
            first[str.charAt(i)]=i;
        }
        int min=length;
        for (int i=65;i<128;i++){
            if (asc[i]==1) {
                if (min>first[i]) min=first[i];
            }
        }
        return min==length?-1:min;
    }


    /**
     * 第一次遍历字符串记录出现的次数，然后第二次顺序遍历字符串，查看是否有只出现一次的字符，有则返回它的次数，没有则返回-1
     */
    public class Solution {
        public int FirstNotRepeatingChar(String str) {
            if(str==null || str.length() == 0)return -1;
            int[] count = new int[256];
            //用一个类似hash的东西来存储字符出现的次数，很方便
            for(int i=0; i < str.length();i++)
                count[str.charAt(i)]++;
            //其实这个第二步应该也是ka我的地方，没有在第一时间想到只要在遍历一遍数组并访问hash记录就可以了
            for(int i=0; i < str.length();i++)
                if(count[str.charAt(i)]==1)
                    return i;
            return -1;
        }
    }
}
