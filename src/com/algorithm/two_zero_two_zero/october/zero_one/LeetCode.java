package com.algorithm.two_zero_two_zero.october.zero_one;

/**
 * @author Ming
 * @date 2020/10/2 - 20:04
 * @describe  19
 */
public class LeetCode {

    /**
     * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves，
     * 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
     * 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。
     * 每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。
     * 请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
     *
     * 输入：leaves = "rrryyyrryyyrr"
     * 输出：2
     * 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
     *
     * 输入：leaves = "ryr"
     * 输出：0
     * 解释：已符合要求，不需要额外操作
     */
    /**
     * 自己的思路：超时
     */
    int number = Integer.MAX_VALUE;
    public int minimumOperations(String leaves) {
        int now = 0;
        if (leaves.charAt(0)=='y') now++;
        if (leaves.charAt(leaves.length()-1)=='y') now++;
        change(0,1,leaves.length()-2,leaves,now);
        return number;
    }

    /**
     *
     * @param type 是什么类型，0代表 红黄红，1代表 黄红 , 2代表红
     * @param start 开始索引
     * @param end 结束索引
     * @param S 字符串
     * @param now 现在更改的次数
     */
    private void change(int type , int start , int end , String S , int now){
        //提前结束
        if (now > number) return;
        //处理完
        if (start==end){
            if (type==0) {
                if (S.charAt(start)=='r')
                    now++;
            } else if (type==2){
                if (S.charAt(start)=='y')
                    now++;
            }
            if (now<number) number = now;
            return;
        }
        switch (type){
            case 0 :
                if (S.charAt(start)=='r'){
                    change(0,start+1,end,S,now);
                }else{
                    //黄色：两种办法，更改颜色或者换一种情况
                    change(0,start+1,end,S,now+1);
                    change(1,start+1,end,S,now);
                }
                break;
            case 1 :
                if (S.charAt(start)=='y'){
                    change(1,start+1,end,S,now);
                }else {
                    //红色：两种办法，更改颜色或者换一种情况
                    change(1,start+1,end,S,now+1);
                    change(2,start+1,end,S,now);
                }
                break;
            case 2:
                if (S.charAt(start)=='y'){
                    change(2,start+1,end,S,now+1);
                }else {
                    change(2,start+1,end,S,now);
                }
                break;
            default:
                return;
        }
    }

    class Solution {
        /**
         * 动态规划
         * 思路：和自己的思路类似，但是采用的比较轻量
         *      [i][j]：
         *          j==0 ：代表还是 红黄红 情况
         *          j==1 ：代表还是 黄红 情况
         *          j==0 ：代表还是 红 情况
         *
         * @param leaves
         * @return
         */
        public int minimumOperations(String leaves) {
            int n = leaves.length();
            int[][] f = new int[n][3];
            f[0][0] = leaves.charAt(0) == 'y' ? 1 : 0;
            f[0][1] = f[0][2] = f[1][2] = Integer.MAX_VALUE;
            for (int i = 1; i < n; ++i) {
                int isRed = leaves.charAt(i) == 'r' ? 1 : 0;
                int isYellow = leaves.charAt(i) == 'y' ? 1 : 0;
                f[i][0] = f[i - 1][0] + isYellow;
                f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]) + isRed;
                if (i >= 2) {
                    f[i][2] = Math.min(f[i - 1][1], f[i - 1][2]) + isYellow;
                }
            }
            return f[n - 1][2];
        }
    }

    class Solution1 {
        /**
         * 前缀和 + 动态规划
         * @param leaves
         * @return
         */
        public int minimumOperations(String leaves) {
            int n = leaves.length();
            int g = leaves.charAt(0) == 'y' ? 1 : -1;
            int gmin = g;
            int ans = Integer.MAX_VALUE;
            for (int i = 1; i < n; ++i) {
                int isYellow = leaves.charAt(i) == 'y' ? 1 : 0;
                g += 2 * isYellow - 1;
                if (i != n - 1) {
                    ans = Math.min(ans, gmin - g);
                }
                gmin = Math.min(gmin, g);
            }
            return ans + (g + n) / 2;
        }
    }

}
