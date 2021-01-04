package com.algorithm.two_zero_two_zero.september.zero_five;

/**
 * @author Ming
 * @date 2020/9/5 - 11:18
 * @describe
 */
public class ThirtyEight {
    /**
     * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     *
     * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
     */

    /**
     * 自己的解法
     * 运行时间：10ms
     *
     * 占用内存：9384k
     *
     * 思路：深度遍历，如果该节点为空则返回0，否则求左右节点的深度，若两者相减的绝对值小于1，则返回左右节点的最大的深度+1，否则返回-1，
     *       同时得先判断左右节点的深度是否有-1，有则一样返回-1.
     *
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        return dfs(root)!=-1;
    }

    private int dfs(TreeNode treeNode){
        if (null==treeNode) return 0;
        int left = dfs(treeNode.left);
        int right = dfs(treeNode.right);
        if (left==-1||right==-1) return -1;
        return Math.abs(left-right)<=1? Math.max(left,right)+1: -1;
    }



}
