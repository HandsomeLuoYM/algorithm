package com.algorithm.two_zero_two_zero.september.two_zero;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Ming
 * @date 2020/9/20 - 11:07
 * @describe
 */
public class SixtyTwo {
    /**
     * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
     */

    /**
     * 自己的思路：存放初始化一个长度为k的排序的队列，然后遍历查找添加（可以不是二叉查找树）
     * 运行时间：18ms
     *
     * 占用内存：9624k
     */
    PriorityQueue<TreeNode> queue;
    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot==null||k<=0) return null;
        queue = new PriorityQueue<>(k,new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                return o2.val-o1.val;
            }
        });
        dfs(pRoot,k);
        return queue.size()==k?queue.peek():null;
    }
    private void dfs(TreeNode treeNode,int k){
        if (treeNode==null) return;
        if (queue.size()<k){
            queue.add(treeNode);

        }else {
            TreeNode peek = queue.peek();
            if (peek.val>treeNode.val){
                queue.remove();
                queue.add(treeNode);
            }
        }
        dfs(treeNode.left,k);
        dfs(treeNode.right,k);
    }

    /**
     * 注意当搜到第k个节点时，如何终止继续向下的无用搜索？
     * 只适用于二叉查找树
     */
    public class Solution {

        TreeNode treeNode = null;
        int count = 0;

        void dfs(TreeNode pRoot, int k) {

            if (count < k && pRoot.left != null) {
                dfs(pRoot.left, k);
            }

            if (++count == k) {
                treeNode = pRoot;
            }

            if (count < k && pRoot.right != null) {
                dfs(pRoot.right, k);
            }
        }

        TreeNode KthNode(TreeNode pRoot, int k) {
            if (pRoot == null || k <= 0) {
                return null;
            }

            dfs(pRoot, k);
            return treeNode;
        }
    }

}
