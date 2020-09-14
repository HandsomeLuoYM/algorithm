package com.algorithm.september.one_two;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/9/12 - 0:53
 * @describe 637. 二叉树的层平均值
 */
public class LeetCode {

    /**
     * 思路：深度遍历
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.96%的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了5.04%的用户
     */
    List<Double> list = new ArrayList<>();
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        add(queue);
        return list;
    }

    public void add(Queue<TreeNode> queue){
        if (queue.isEmpty()) return;
        int all = 0,length = queue.size();
        TreeNode now ;
        Queue<TreeNode> newQueue = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            now = queue.poll();
            all+=now.val;
            if (now.left!=null) newQueue.add(now.left);
            if (now.right!=null) newQueue.add(now.right);
        }
        list.add(1.0*all/length);
        add(newQueue);
    }
}
