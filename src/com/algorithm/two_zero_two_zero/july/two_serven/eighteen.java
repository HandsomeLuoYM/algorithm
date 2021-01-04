package com.algorithm.two_zero_two_zero.july.two_serven;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/7/27 - 15:15
 * @describe
 */
public class eighteen {

    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     * 二叉树的镜像定义：源二叉树
     *     	    8
     *     	   /  \
     *     	  6   10
     *     	 / \  / \
     *     	5  7 9 11
     *     	镜像二叉树
     *     	    8
     *     	   /  \
     *     	  10   6
     *     	 / \  / \
     *     	11 9 7  5
     */

    /**
     * 运行时间：19ms
     *
     * 占用内存：9728k
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root==null) return;
        TreeNode tempTree;
        tempTree = root.left;
        root.left=root.right;
        root.right = tempTree;
        Mirror(root.left);
        Mirror(root.right);
    }



        public void Mirror2(TreeNode pRoot) {

            Queue<TreeNode> pq = new LinkedList<TreeNode>();
            pq.add(pRoot);
            while (!pq.isEmpty()) {
                int sz = pq.size();
                while (sz--!=0) {
                    TreeNode node = pq.poll();

                    if (node.left!=null) pq.add(node.left);
                    if (node.right!=null) pq.add(node.right);
                    // our tasks
                    TreeNode cur = node.left; // 需要保存一下
                    node.left = node.right;
                    node.right = cur;


                } // end inner while

            } // end outer while
        }

}

