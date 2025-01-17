package com.algorithm.two_zero_two_zero.july.two_eight;

/**
   @author Ming
   @date 2020/7/28 - 11:20
   @describe
 **/
public class LeetCode {

    /**
       给定一个二叉树，找出其最大深度。

       二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

       说明: 叶子节点是指没有子节点的节点。

       示例：
       给定二叉树 [3,9,20,null,null,15,7]，

           3
          / \
         9  20
           /  \
          15   7
       返回它的最大深度 3 。

      */

    public int maxDepth(TreeNode root) {
        if (root==null) {
            return 0;
        } else if(root.left==null&&root.right==null) {
            return 1;
        } else {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return left>right ? (left+1):(right+1);
        }
    }

}
