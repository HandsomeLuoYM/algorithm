package com.algorithm.september.one_serven;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/17 - 22:59
 * @describe
 */
public class FiftySeven {

    /**
     * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
     * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
     */

    /**
     * 自己的做法：找到根节点，然后中序遍历，再查找 pNode 的下一个节点
     *
     * 运行时间：15ms
     *
     * 占用内存：9752k
     */
    List<TreeLinkNode> linkNodes = new ArrayList<>();
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode==null) return null;
        TreeLinkNode par = pNode;
        while(par.next != null){
            par = par.next;
        }
        dfs(par);
        int length = linkNodes.size();
        for (int i=0;i<length;i++){
            if(pNode == linkNodes.get(i)){
                return i == linkNodes.size()-1?null:linkNodes.get(i+1);
            }
        }
        return null;
    }

    public void dfs(TreeLinkNode now){
        if (now==null) return;
        dfs(now.left);
        linkNodes.add(now);
        dfs(now.right);
    }

    /**
     * 直接查找（以该二叉树为例，中序遍历为：{D,B,H,E,I,A,F,C,G}）
     *
     * 有右子树，下一结点是右子树中的最左结点，例如 B，下一结点是 H
     *
     * 无右子树，且结点是该结点父结点的左子树，则下一结点是该结点的父结点，例如 H，下一结点是 E
     *
     * 无右子树，且结点是该结点父结点的右子树，则我们一直沿着父结点追朔，直到找到某个结点是其父结点的左子树，
     * 如果存在这样的结点，那么这个结点的父结点就是我们要找的下一结点。例如 I，下一结点是 A；例如 G，并没有符合情况的结点，所以 G 没有下一结点
     */
    public class Solution {

        public TreeLinkNode GetNext(TreeLinkNode pNode) {
            // 1.有右子树
            if (pNode.right != null) {
                TreeLinkNode pRight = pNode.right;
                while (pRight.left != null) {
                    pRight = pRight.left;
                }
                return pRight;
            }
            // 2.无右子树，而且它为父节点的左子树
            if (pNode.next != null && pNode.next.left == pNode) {
                return pNode.next;
            }
            // 3.无右子树，且结点是该结点父结点的右子树
            if (pNode.next != null) {
                //他的父节点
                TreeLinkNode pNext = pNode.next;
                while (pNext.next != null && pNext.next.right == pNext) {
                    pNext = pNext.next;
                }
                return pNext.next;
            }
            return null;
        }
    }

}
