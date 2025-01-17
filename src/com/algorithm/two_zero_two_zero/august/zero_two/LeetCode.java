package com.algorithm.two_zero_two_zero.august.zero_two;

import java.util.ArrayList;
import java.util.List;

/**
   @author Ming
   @date 2020/8/2 - 16:38
   @describe
  */
public class LeetCode {

    /**
       给定一个二叉树，原地将它展开为一个单链表。
       还需要修改左右子树
       @param root
      */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        sort(list,root);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    private void sort(List list,TreeNode treeNode){
        if (treeNode!=null){
            list.add(treeNode);
            sort(list,treeNode.left);
            sort(list,treeNode.right);
        }
    }

    /***
      注意到前序遍历访问各节点的顺序是根节点、左子树、右子树。
       如果一个节点的左子节点为空，则该节点不需要进行展开操作。
       如果一个节点的左子节点不为空，则该节点的左子树中的最后一个节点被访问之后，该节点的右子节点被访问。
       该节点的左子树中最后一个被访问的节点是左子树中的最右边的节点，也是该节点的前驱节点。
       因此，问题转化成寻找当前节点的前驱节点。

       具体做法是，对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点
       ，作为前驱节点，将当前节点的右子节点赋给前驱节点的右子节点，然后将当前节点的左子节点赋给当前节点的右子节点，
       并将当前节点的左子节点设为空。对当前节点处理结束后，继续处理链表中的下一个节点，直到所有节点都处理结束。

       @param root
      */
    public void flatten2(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }



}
