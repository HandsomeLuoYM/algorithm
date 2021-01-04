package com.algorithm.two_zero_two_zero.october.one_two;

/**
 * @author Ming
 * @date 2020/10/12 - 16:03
 * @describe 530. 二叉搜索树的最小绝对差
 */
public class LeetCode {

    /**
     *
     * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值
     *
     * 输入：
     *
     *    1
     *     \
     *      3
     *     /
     *    2
     *
     * 输出：
     * 1
     *
     * 解释：
     * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
     *
     */


    /**
     * 自己的思路：中序遍历
     * 执行用时：1 ms, 在所有 Java 提交中击败了82.39%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了98.44%的用户
     */
    int min = Integer.MAX_VALUE;
    Integer last = null;
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }
    void dfs(TreeNode node){
        if (node.left!=null) dfs(node.left);
        if (last!=null && node.val-last < min) min = node.val-last;
        last = node.val;
        if (node.right!=null) dfs(node.right);
    }

}
