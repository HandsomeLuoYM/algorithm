package com.algorithm.september.one_nine;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/9/19 - 13:23
 * @describe
 */
public class SixtyOne {
    /**
     * 请实现两个函数，分别用来序列化和反序列化二叉树
     *
     * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
     * 从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，
     * 序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
     *
     * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
     *
     * 例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，然后通过自己的函数来解析回这个二叉树
     */

    /**
     * 思路：层次存储，在string中将各个元素用 “ , ”分开，空则标识为 “ # ”。
     *       在分解序列化时，由于树索引的规律，第i个元素的左子树为 2i ，右子树为 2i+1
     * 运行时间：18ms
     *
     * 占用内存：9780k
     */
    String Serialize(TreeNode root) {
        String all = "#,";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int length;
        ArrayList<Integer> arrayList;
        TreeNode treeNode;
        boolean flag = true;
        while (!queue.isEmpty()&&flag){
            flag = false;
            length = queue.size();
            arrayList = new ArrayList<>();
            for (int i = 0;i<length;i++){
                treeNode = queue.poll();
                all+= treeNode==null?"#," : (treeNode.val+",");
                if (treeNode!=null){
                    queue.offer(treeNode.left);
                    queue.offer(treeNode.right);
                }else {
                    queue.offer(null);
                    queue.offer(null);
                }
                if (treeNode!=null) flag=true;
            }
        }
        return all;
    }
    TreeNode Deserialize(String str) {
        return change(str.split(",") ,1);
    }
    private TreeNode change(String[] str, int index){
        if (str.length<=index||str[index].equals("#")) return null;
        TreeNode treeNode = new TreeNode(Integer.parseInt(str[index]));
        treeNode.left = change(str,2*index);
        treeNode.right = change(str,2*index+1);
        return treeNode;
    }

}
