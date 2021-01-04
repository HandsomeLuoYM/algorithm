package com.algorithm.two_zero_two_zero.august.zero_three;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
   @author Ming
   @date 2020/8/3 - 11:59
   @describe
  */
public class twety_two {
    /**
       从上往下打印出二叉树的每个节点，同层节点从左至右打印
      */
    /**
       运行时间：16ms

       占用内存：9612k
       @param root
       @return
      */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        ArrayList result = new ArrayList<Integer>();
        if (root!=null) queue.add(root);
        while (!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            result.add(treeNode.val);
            if (treeNode.left!=null) queue.add(treeNode.left);
            if (treeNode.right!=null) queue.add(treeNode.right);
        }
        return result;
    }
}
