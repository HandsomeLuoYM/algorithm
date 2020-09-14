package com.algorithm.august.zero_four;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/8/4 - 11:04
 * @describe
 */
public class LeetCode {

    /**
     * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
     * <p>
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
     * <p>
     * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
     * 深度优先遍历
     */

    /**
     * 深度遍历
     * @param numCourses
     * @param prerequisites
     * @return
     * 统计课程安排图中每个节点的入度，生成 入度表 indegrees。
     * 借助一个队列 queue，将所有入度为 00 的节点入队。
     * 当 queue 非空时，依次将队首节点出队，在课程安排图中删除此节点 pre：
     * 并不是真正从邻接表中删除此节点 pre，而是将此节点对应所有邻接节点 cur 的入度 -1−1，即 indegrees[cur] -= 1。
     * 当入度 -1−1后邻接节点 cur 的入度为 00，说明 cur 所有的前驱节点已经被 “删除”，此时将 cur 入队。
     * 在每次 pre 出队时，执行 numCourses--；
     * 若整个课程安排图是有向无环图（即可以安排），则所有节点一定都入队并出队过，即完成拓扑排序。换个角度说，若课程安排图中存在环，一定有节点的入度始终不为 00。
     * 因此，拓扑排序出队次数等于课程个数，返回 numCourses == 0 判断课程是否可以成功安排。
     *
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //标记入度
        int[] indegrees = new int[numCourses];
        //入度
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        //赋初值
        for(int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        //[0][先完成]
        for(int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            //标记出度
            adjacency.get(cp[1]).add(cp[0]);
        }
        //遍历找出可以访问的点
        for(int i = 0; i < numCourses; i++) {
            if(indegrees[i] == 0) {
                queue.add(i);
            }
        }
        // BFS TopSort.
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for(int cur : adjacency.get(pre)) {
                if(--indegrees[cur] == 0) {
                    queue.add(cur);
                }
            }
        }
        return numCourses == 0;
    }

    /**
     * 深度遍历
     * @param numCourses
     * @param prerequisites
     * @return
     * 借助一个标志列表 flags，用于判断每个节点 i （课程）的状态：
     * 未被 DFS 访问：i == 0；
     * 已被其他节点启动的 DFS 访问：i == -1；
     * 已被当前节点启动的 DFS 访问：i == 1。
     *
     * 对 numCourses 个节点依次执行 DFS，判断每个节点起步 DFS 是否存在环，若存在环直接返回 FalseFalse。DFS 流程；
     * 终止条件：
     *      当 flag[i] == -1，说明当前访问节点已被其他节点启动的 DFS 访问，无需再重复搜索，直接返回 TrueTrue。
     *      当 flag[i] == 1，说明在本轮 DFS 搜索中节点 i 被第 22 次访问，即 课程安排图有环 ，直接返回 FalseFalse。
     *      将当前访问节点 i 对应 flag[i] 置 11，即标记其被本轮 DFS 访问过；
     *      递归访问当前节点 i 的所有邻接节点 j，当发现环直接返回 FalseFalse；
     *      当前节点所有邻接节点已被遍历，并没有发现环，则将当前节点 flag 置为 -1−1 并返回 True。
     *
     * 若整个图 DFS 结束并未发现环，返回 True。
     *
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adjacency = new ArrayList<>();
        //初始化list数组
        for(int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        //标记
        int[] flags = new int[numCourses];
        //初始化出度
        for(int[] cp : prerequisites) {
            adjacency.get(cp[1]).add(cp[0]);
        }
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(adjacency, flags, i)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if(flags[i] == 1) {
            return false;
        }
        if(flags[i] == -1) {
            return true;
        }
        flags[i] = 1;
        //遍历他的出度，再递归调用
        for(Integer j : adjacency.get(i)) {
            //递归调用
            if(!dfs(adjacency, flags, j)) {
                return false;
            }
        }
        flags[i] = -1;
        return true;
    }


    public static void main(String[] args) {

    }


}
