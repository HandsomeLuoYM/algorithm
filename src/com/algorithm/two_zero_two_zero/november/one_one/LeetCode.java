package com.algorithm.two_zero_two_zero.november.one_one;

import java.util.*;

/**
 * @author Ming
 * @date 2020/11/11 - 1:36
 * @describe 514、自由之路
 */
public class LeetCode {

    /**
     * 视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
     *
     * 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
     *
     * 最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
     *
     * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
     *
     * 您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
     * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
     *
     * 输入: ring = "godding", key = "gd"
     * 输出: 4
     * 解释:
     *  对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
     *  对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
     *  当然, 我们还需要1步进行拼写。
     *  因此最终的输出是 4。
     *
     */
    /**
     * 执行用时：13 ms, 在所有 Java 提交中击败了62.76%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了63.24%的用户
     */
    //字符出现的位置
    Map<Character,List<Integer>> mp;
    int[][] dp;
    public int findRotateSteps(String ring, String key) {
        int rl=ring.length();//转盘的长度
        int kl=key.length();//钥匙的长度
        if(kl==0) return 0;
        char[] r=ring.toCharArray();
        char[] k=key.toCharArray();
        mp = new HashMap<>();
        //保存字符出现的位置
        for(int i=0;i<rl;i++){
            if(mp.containsKey(r[i])){
                mp.get(r[i]).add(i);
            } else{
                List<Integer> next= new ArrayList<>();
                next.add(i);
                mp.put(r[i],next);
            }
        }
        //动态规划数组
        dp = new int[kl][rl];
        List<Integer> next2 = mp.get(k[0]);
        Iterator<Integer> it2 = next2.iterator();
        while(it2.hasNext()){
            int c = it2.next();
            int m = Math.min(c,rl-c);//找到每个位置
            dp[0][c]=m+1;
        }
        for(int i=1;i<kl;i++){
            //拿到本次字符的所有位置
            List<Integer> next = mp.get(k[i]);
            Iterator<Integer> it = next.iterator();
            //遍历整个位置
            while(it.hasNext()){
                int c = it.next();//找到本次的所有位置
                int min=Integer.MAX_VALUE;
                List<Integer> next1 = mp.get(k[i-1]);
                Iterator<Integer> it1 = next1.iterator();
                //拿到上次字符的所有位置
                while(it1.hasNext()){
                    int d = it1.next();//找到上个字符所有的位置来计算
                    //上次到这次的最小距离
                    int m = Math.min(rl-c+d,rl-d+c);
                    m = Math.min(m,Math.abs(c-d));
                    //获取最小值
                    min=Math.min(min,dp[i-1][d]+m+1);

                }
                dp[i][c]=min;
            }
        }
        int ans=Integer.MAX_VALUE;
        //获取最后一个字符的次数数组
        List<Integer> next = mp.get(k[kl-1]);
        Iterator<Integer> it = next.iterator();
        //遍历求最小值
        while(it.hasNext()){
            ans=Math.min(ans,dp[kl-1][it.next()]);
        }
        return ans;
    }
}
