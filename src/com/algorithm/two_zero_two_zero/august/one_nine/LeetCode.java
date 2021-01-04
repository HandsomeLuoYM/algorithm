package com.algorithm.two_zero_two_zero.august.one_nine;

/**
 * @author Ming
 * @date 2020/8/19 - 22:58
 * @describe  647
 */
public class LeetCode {


    /**
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     *
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串
     *
     * 输入："abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     *
     * 输入："aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     */

    /**
     * 自己的解法
     * 执行用时：4 ms, 在所有 Java 提交中击败了83.74%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了58.26%的用户
     */
    public int countSubstrings(String s) {
        if (s.length()==0) return 0;
        int all=1;
        int length = s.length();
        for (int i=1;i<length;i++){
            all++;
            for (int j = 0; i-1-j >= 0 && i+j<length;j++){
                if (s.charAt(i-1-j)==s.charAt(i+j)){
                    all++;
                    continue;
                }
                break;
            }
            for (int j=0;i-1-j>=0 && i+j+1<length;j++){
                if (s.charAt(i-1-j)==s.charAt(i+j+1)){
                    System.out.println(s.charAt(i-1-j)+"=="+s.charAt(i+j+1));
                    all++;
                    continue;
                }
                break;
            }
        }
        return all;
    }

    /**
     * 官方题解 1
     *计算有多少个回文子串的最朴素方法就是枚举出所有的回文子串，而枚举出所有的回文字串又有两种思路，分别是：
     *      枚举出所有的子串，然后再判断这些子串是否是回文；
     *      枚举每一个可能的回文中心，然后用两个指针分别向左右两边拓展，当两个指针指向的元素相同的时候就拓展，否则停止拓展。
     */
    public int countSubstrings1(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

    /**
     * Manacher 算法是在线性时间内求解最长回文子串的算法。在本题中，我们要求解回文串的个数，
     * 为什么也能使用 Manacher 算法呢？这里我们就需要理解一下 Manacher 的基本原理。
     *
     * Manacher 算法也会面临「方法一」中的奇数长度和偶数长度的问题，它的处理方式是在所有的相邻字符中间插入 \##，
     * 比如 abaa 会被处理成 #a#b#a#a#，这样可以保证所有找到的回文串都是奇数长度的，以任意一个字符为回文中心，
     * 既可以包含原来的奇数长度的情况，也可以包含原来偶数长度的情况。假设原字符串为 SS，经过这个处理之后的字符串为 ss。
     */
    public int countSubstrings2(String s) {
        int n = s.length();
        StringBuffer t = new StringBuffer("$#");
        for (int i = 0; i < n; ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        n = t.length();
        t.append('!');

        int[] f = new int[n];
        int iMax = 0, rMax = 0, ans = 0;
        for (int i = 1; i < n; ++i) {
            // 初始化 f[i]
            f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
            // 中心拓展
            while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
                ++f[i];
            }
            // 动态维护 iMax 和 rMax
            if (i + f[i] - 1 > rMax) {
                iMax = i;
                rMax = i + f[i] - 1;
            }
            // 统计答案, 当前贡献为 (f[i] - 1) / 2 上取整
            ans += f[i] / 2;
        }

        return ans;
    }



}
