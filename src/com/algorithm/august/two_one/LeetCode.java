package com.algorithm.august.two_one;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/8/21 - 2:41
 * @describe 111
 */
public class LeetCode {
    /**
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     */

    /**
     * 深度优先算法（自己做的）
     *执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了74.28%的用户
     */
    public int minDepth(TreeNode root) {
        if (root==null) return 0;
        return min(root);
    }
    public int min(TreeNode root){
        if (root==null) return Integer.MAX_VALUE;
        if (root.left==null&&root.right==null) return 1;
        else{
            int left = minDepth(root.left),right=minDepth(root.right);
            return left>right?right+1:left+1;
        }
    }

    ////////////////////////////////////////  官方题解二 广度优先算法  //////////////////////////////////////
    //存储数据的结构体
    class QueueNode {
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<QueueNode> queue = new LinkedList<QueueNode>();
        queue.offer(new QueueNode(root, 1));
        while (!queue.isEmpty()) {
            QueueNode nodeDepth = queue.poll();
            TreeNode node = nodeDepth.node;
            int depth = nodeDepth.depth;
            //谁先为空谁最短
            if (node.left == null && node.right == null) {
                return depth;
            }
            if (node.left != null) {
                queue.offer(new QueueNode(node.left, depth + 1));
            }
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, depth + 1));
            }
        }

        return 0;
    }

}
