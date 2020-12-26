package com.algorithm.december.二叉树的锯齿形层序遍历;


import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @author Ming
 * @date 2020/12/22 - 0:12
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * 例如：
     *
     * 给定二叉树 [3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     *
     * 返回锯齿形层序遍历如下：
     * [
     *      [3],
     *      [20,9],
     *      [15,7]
     * ]
     */
    /**
     * 自己的做法：
     *      设置一个栈存储顺序节点信息
     * 执行用时：2 ms, 在所有 Java 提交中击败了13.10%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了65.08%的用户
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<TreeNode>(){{
                add(root);
        }};
        boolean flag = true;
        while (!stack.isEmpty()){
            List<Integer> param = new ArrayList<>();
            Stack<TreeNode> newStack = new Stack<TreeNode>();
            if (flag){
                while (!stack.isEmpty()){
                    TreeNode pop = stack.pop();
                    param.add(pop.val);
                    if (pop.left != null) newStack.push(pop.left);
                    if (pop.right != null) newStack.push(pop.right);
                }
                flag = false;
            }else {
                while (!stack.isEmpty()){
                    TreeNode pop = stack.pop();
                    param.add(pop.val);
                    if (pop.right != null) newStack.push(pop.right);
                    if (pop.left != null) newStack.push(pop.left);
                }
                flag = true;
            }
            stack = newStack;
            list.add(param);
        }
        return list;
    }

    /**
     * 官方题解：和我思路大致相同，采用队列来存储，并且设置一个标志标识是否队列的插入情况
     */
    class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> ans = new LinkedList<List<Integer>>();
            if (root == null) {
                return ans;
            }

            Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
            nodeQueue.offer(root);
            boolean isOrderLeft = true;

            while (!nodeQueue.isEmpty()) {
                Deque<Integer> levelList = new LinkedList<Integer>();
                int size = nodeQueue.size();
                for (int i = 0; i < size; ++i) {
                    TreeNode curNode = nodeQueue.poll();
                    if (isOrderLeft) {
                        levelList.offerLast(curNode.val);
                    } else {
                        levelList.offerFirst(curNode.val);
                    }
                    if (curNode.left != null) {
                        nodeQueue.offer(curNode.left);
                    }
                    if (curNode.right != null) {
                        nodeQueue.offer(curNode.right);
                    }
                }
                ans.add(new LinkedList<Integer>(levelList));
                isOrderLeft = !isOrderLeft;
            }

            return ans;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
