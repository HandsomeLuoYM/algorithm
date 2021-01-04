package com.algorithm.two_zero_two_zero.august.zero_serven;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/8/7 - 14:54
 * @describe
 */
public class LeetCode {

    /**
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     *
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     */

    /**
     * 深度优先算法
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了34.10%的用户
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null&&q==null) return true;
        if (p!=null && q!= null && p.val == q.val) return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
        else return false;
    }

    /**
     * 广度优先算法
     */
    class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            } else if (p == null || q == null) {
                return false;
            }
            Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
            Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
            queue1.offer(p);
            queue2.offer(q);
            while (!queue1.isEmpty() && !queue2.isEmpty()) {
                TreeNode node1 = queue1.poll();
                TreeNode node2 = queue2.poll();
                if (node1.val != node2.val) {
                    return false;
                }
                TreeNode left1 = node1.left, right1 = node1.right, left2 = node2.left, right2 = node2.right;
                if (left1 == null ^ left2 == null) {
                    return false;
                }
                if (right1 == null ^ right2 == null) {
                    return false;
                }
                if (left1 != null) {
                    queue1.offer(left1);
                }
                if (right1 != null) {
                    queue1.offer(right1);
                }
                if (left2 != null) {
                    queue2.offer(left2);
                }
                if (right2 != null) {
                    queue2.offer(right2);
                }
            }
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }
}
