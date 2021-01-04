package com.algorithm.two_zero_two_zero.august.zero_eight;


import java.util.ArrayList;
import java.util.List;

/**
   @author Ming
   @date 2020/8/8 - 16:42
   @describe
  */
public class LeetCode {
    /**
       二叉搜索树中的两个节点被错误地交换。

       请在不改变其结构的情况下，恢复这棵树。
      */
    public void recoverTree(TreeNode root) {
        List<Integer> nums = new ArrayList<Integer>();
        inorder(root, nums);
        int[] swapped = findTwoSwapped(nums);
        recover(root, 2, swapped[0], swapped[1]);
    }

    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public int[] findTwoSwapped(List<Integer> nums) {
        int n = nums.size();
        int x = -1, y = -1;
        for (int i = 0; i < n - 1; ++i) {
            if (nums.get(i + 1) < nums.get(i)) {
                y = nums.get(i + 1);
                if (x == -1) {
                    x = nums.get(i);
                } else {
                    break;
                }
            }
        }
        return new int[]{x, y};
    }

    public void recover(TreeNode root, int count, int x, int y) {
        if (root != null) {
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;
                if (--count == 0) {
                    return;
                }
            }
            recover(root.right, count, x, y);
            recover(root.left, count, x, y);
        }
    }

    ////////////////////////////////////////////////

    /**
       二叉搜索树按中序遍历，节点一定是递增的。所以通过比找出逆序对，就是被交换的节点。
       有两种情况：1）中序遍历中，相邻两节点交换 2）中序遍历中，非相邻节点交换。

       执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
       内存消耗：40.2 MB, 在所有 Java 提交中击败了23.30%的用户
      */
    TreeNode x, y, pred;

    public void recoverTree2(TreeNode root) {
        if (root == null) {
            return;
        }

        findSwapped(root);
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    void findSwapped(TreeNode node) {
        if (node == null) {
            return;
        }
        findSwapped(node.left);
        if (pred != null && pred.val > node.val) {
            y = node;
            if (x != null) {
                return;
            }
            x = pred;
        }
        pred = node;
        findSwapped(node.right);
    }


}
