package com.algorithm.two_zero_two_zero.september.two_five;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming
 * @date 2020/9/25 - 12:32
 * @describe
 */
public class LeetCode {

    /**
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     *
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     *
     *      中序遍历 inorder = [9,3,15,20,7]
     *      后序遍历 postorder = [9,15,7,20,3]
     *
     * 返回如下的二叉树：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     */
    /**
     * 自己的做法：
     *      后序倒着来，后续的每个都可以吧中序的拆分成两个，当相等时即就只有一个节点
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.91%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了89.08%的用户
     */
    int index;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        index = inorder.length-1;
        return dfs(0,inorder.length-1,inorder,postorder);
    }


    TreeNode dfs(int start, int end, int[] inorder, int[] postorder){
        if (start > end) return null;
        if (start == end) {
            index--;
            return new TreeNode(inorder[start]);
        }
        TreeNode treeNode = new TreeNode(postorder[index]);
        index--;
        for (int i = end;i>=start;i--){
            if (postorder[index+1]==inorder[i]){
                treeNode.right = dfs(i+1, end, inorder, postorder);
                treeNode.left = dfs(start, i-1, inorder, postorder);
                break;
            }
        }
        return treeNode;
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        int post_idx;
        int[] postorder;
        int[] inorder;
        Map<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

        public TreeNode helper(int in_left, int in_right) {
            // 如果这里没有节点构造二叉树了，就结束
            if (in_left > in_right) {
                return null;
            }

            // 选择 post_idx 位置的元素作为当前子树根节点
            int root_val = postorder[post_idx];
            TreeNode root = new TreeNode(root_val);

            // 根据 root 所在位置分成左右两棵子树
            int index = idx_map.get(root_val);

            // 下标减一
            post_idx--;
            // 构造右子树
            root.right = helper(index + 1, in_right);
            // 构造左子树
            root.left = helper(in_left, index - 1);
            return root;
        }

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            this.postorder = postorder;
            this.inorder = inorder;
            // 从后序遍历的最后一个元素开始
            post_idx = postorder.length - 1;

            // 建立（元素，下标）键值对的哈希表
            int idx = 0;
            for (Integer val : inorder) {
                idx_map.put(val, idx++);
            }

            return helper(0, inorder.length - 1);
        }

    }

}
