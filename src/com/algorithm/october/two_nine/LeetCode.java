package com.algorithm.october.two_nine;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/10/29 - 1:24
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     * 计算从根到叶子节点生成的所有数字之和。
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 输入: [1,2,3]
     * 1
     * / \
     * 2   3
     * 输出: 25
     * 解释:
     * 从根到叶子节点路径 1->2 代表数字 12.
     * 从根到叶子节点路径 1->3 代表数字 13.
     * 因此，数字总和 = 12 + 13 = 25.
     * <p>
     * 输入: [4,9,0,5,1]
     * 4
     * / \
     * 9   0
     *  / \
     * 5   1
     * 输出: 1026
     * 解释:
     * 从根到叶子节点路径 4->9->5 代表数字 495.
     * 从根到叶子节点路径 4->9->1 代表数字 491.
     * 从根到叶子节点路径 4->0 代表数字 40.
     * 因此，数字总和 = 495 + 491 + 40 = 1026.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 自己的做法：
     *      深度优先，递归添加
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了90.98%的用户
     */
    int all = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 0);
        return all;
    }

    void dfs(TreeNode node, int now){
        if (node.left == null && node.right == null) {
            all += now*10+node.val;
            return;
        }
        if (node.right != null) dfs(node.right, now*10+node.val);
        if (node.left != null) dfs(node.left, now*10+node.val);
    }

    /**
     * 广度优先：利用两个队列来存储改节点与该节点对应的num值
     */
    class Solution {
        public int sumNumbers(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int sum = 0;
            Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
            Queue<Integer> numQueue = new LinkedList<Integer>();
            nodeQueue.offer(root);
            numQueue.offer(root.val);
            while (!nodeQueue.isEmpty()) {
                TreeNode node = nodeQueue.poll();
                int num = numQueue.poll();
                TreeNode left = node.left, right = node.right;
                if (left == null && right == null) {
                    sum += num;
                } else {
                    if (left != null) {
                        nodeQueue.offer(left);
                        numQueue.offer(num * 10 + left.val);
                    }
                    if (right != null) {
                        nodeQueue.offer(right);
                        numQueue.offer(num * 10 + right.val);
                    }
                }
            }
            return sum;
        }
    }

}
