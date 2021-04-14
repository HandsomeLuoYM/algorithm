package com.algorithm.two_zero_two_one.february.二叉树的右视图;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @author Ming
 * @date 2021/2/2 - 11:39
 * @describe
 */
public class LeetCode {
    /**
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1, 3, 4]
     * 解释:
     * <p>
     * 1            <---
     * /   \
     * 2     3         <---
     * \     \
     * 5     4       <---
     */
    /**
     * 思路：广度优先遍历，遍历时记录
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.99%的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了79.33%的用户
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rs = new ArrayList<>();
        if (root == null) return rs;
        Queue<TreeNode> queue = new LinkedList<>();
        int number;
        TreeNode node;
        queue.add(root);
        while (!queue.isEmpty()) {
            number = queue.size();
            rs.add(queue.peek().val);
            while (number > 0) {
                node = queue.poll();
                if (node.right != null) queue.add(node.right);
                if (node.left != null) queue.add(node.left);
                number--;
            }
        }
        return rs;
    }

    /**
     * 深度优先遍历
     */
    class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
            int max_depth = -1;
            //一个存储节点，一个存储节点的深度
            Stack<TreeNode> nodeStack = new Stack<TreeNode>();
            Stack<Integer> depthStack = new Stack<Integer>();
            nodeStack.push(root);
            depthStack.push(0);

            while (!nodeStack.isEmpty()) {
                TreeNode node = nodeStack.pop();
                int depth = depthStack.pop();

                if (node != null) {
                    // 维护二叉树的最大深度
                    max_depth = Math.max(max_depth, depth);

                    // 如果不存在对应深度的节点我们才插入
                    if (!rightmostValueAtDepth.containsKey(depth)) {
                        rightmostValueAtDepth.put(depth, node.val);
                    }

                    nodeStack.push(node.left);
                    nodeStack.push(node.right);
                    depthStack.push(depth + 1);
                    depthStack.push(depth + 1);
                }
            }

            List<Integer> rightView = new ArrayList<Integer>();
            for (int depth = 0; depth <= max_depth; depth++) {
                rightView.add(rightmostValueAtDepth.get(depth));
            }

            return rightView;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
