package com.algorithm.two_zero_two_zero.august.three_one;

import java.util.*;

/**
 * @author Ming
 * @date 2020/8/31 - 9:57
 * @describe
 */
public class LeetCode {

    /**
     * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
     *
     * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
     *
     * 最初，除 0 号房间外的其余所有房间都被锁住。
     *
     * 你可以自由地在房间之间来回走动。
     *
     * 如果能进入每个房间返回 true，否则返回 false。
     *
     */

    /**
     * 自己的题解
     * 执行用时：2 ms, 在所有 Java 提交中击败了76.33%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了57.22%的用户
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms.isEmpty()) return false;
        //存放当前钥匙
        List<Integer> list = new ArrayList<Integer>();
        int[] status = new int[rooms.size()];
        status[0]=1;
        List<Integer> room0 = rooms.get(0);
        list.addAll(room0);

        while (!list.isEmpty()){
            Integer now = list.get(0);
            list.remove(0);
            if (status[now]==0){
                list.addAll(rooms.get(now));
                status[now]=1;
            }
        }
        for (Integer i : status){
            if (i==0) return false;
        }
        return true;
    }

    ///////////////////////////////////  官方题解一（深度遍历）  //////////////////////////////////////////
    class Solution {
        boolean[] vis;
        int num;

        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            int n = rooms.size();
            num = 0;
            vis = new boolean[n];
            dfs(rooms, 0);
            return num == n;
        }

        public void dfs(List<List<Integer>> rooms, int x) {
            vis[x] = true;
            num++;
            for (int it : rooms.get(x)) {
                if (!vis[it]) {
                    dfs(rooms, it);
                }
            }
        }
    }

    //////////////////////////////  官方题解二（广度遍历）  ////////////////////////////////////
    class Solution1 {
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            int n = rooms.size(), num = 0;
            boolean[] vis = new boolean[n];
            Queue<Integer> que = new LinkedList<Integer>();
            vis[0] = true;
            que.offer(0);
            while (!que.isEmpty()) {
                int x = que.poll();
                num++;
                for (int it : rooms.get(x)) {
                    if (!vis[it]) {
                        vis[it] = true;
                        que.offer(it);
                    }
                }
            }
            return num == n;
        }
    }

}
