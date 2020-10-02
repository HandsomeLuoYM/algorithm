package com.algorithm.september.two_six;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/26 - 21:35
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 给定如下二叉树，以及目标和 sum = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     *
     * 返回:
     *
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     *
     */

    /**
     * 思路：
     *      回溯遍历，当相等时添加，然后左右子树都进行回溯
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了59.75%的用户
     */
    List<List<Integer>> lists = new ArrayList<>();
    int all = 0;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        add(root,sum,new ArrayList<>());
        return lists;
    }
    void add(TreeNode node,int sum,List<Integer> nowList){
        if (node==null) return;
        all += node.val;
        nowList.add(node.val);
        if (node.right == null && node.left == null && all == sum) lists.add(new ArrayList<>(nowList));
        add( node.right, sum,nowList);
        add( node.left, sum,nowList);
        all -= node.val;
        nowList.remove(nowList.size()-1);
    }

}
