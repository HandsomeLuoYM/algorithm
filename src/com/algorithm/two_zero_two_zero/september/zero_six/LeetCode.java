package com.algorithm.two_zero_two_zero.september.zero_six;

import java.util.*;

/**
 * @author Ming
 * @date 2020/9/6 - 10:28
 * @describe 107. 二叉树的层次遍历 II
 */
public class LeetCode {

    /**
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * 给定二叉树 [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *  返回其自底向上的层次遍历为：
     *    [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     */

    /**
     * 自己的解法：
     *
     * 思路：设置一个list存放答案，然后一层一层遍历，遍历时，把该层的Node添加到队列中，当队列为空时，递归遍历
     *       添加List时需要慢一些，这一就不用修改顺序
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.25%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了31.59%的用户
     */
    List<List<Integer>> all = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root==null) return all;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        dfs(queue);
        return all;
    }
    public void dfs( Queue<TreeNode> queue){
        if (queue.isEmpty()) return;
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> newQueue = new LinkedList<>();
        TreeNode node;
        while (!queue.isEmpty()){
            node = queue.poll();
            list.add(node.val);
            if(node.left!=null) newQueue.add(node.left);
            if(node.right!=null) newQueue.add(node.right);
        }
        dfs(newQueue);
        all.add(list);
    }


    /**
     * 广度遍历
     *
     * 思路：需要记录好每层他的节点数，然后和深度遍历思想一样
     *
     */
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        //边界条件判断
        if (root == null)
            return new ArrayList<>();
        //队列
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        //根节点入队
        queue.add(root);
        //如果队列不为空就继续循环
        while (!queue.isEmpty()) {
            //BFS打印，levelNum表示的是每层的结点数
            int levelNum = queue.size();
            //subList存储的是每层的结点值
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < levelNum; i++) {
                //出队
                TreeNode node = queue.poll();
                subList.add(node.val);
                //左右子节点如果不为空就加入到队列中
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            //把每层的结点值存储在res中，（从下往上打印，关键点在这）
            res.add(0,subList);
        }
        return res;
    }

}
