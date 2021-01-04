package com.algorithm.two_zero_two_zero.september.one_eight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/18 - 11:00
 * @describe 对称二叉树
 */
public class FiftyEight {

    /**
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
     */

    /**
     * 自己的做法：用一个List保存每一层的节点信息，对称来对比
     * 运行时间：12ms
     *
     * 占用内存：9536k
     *
     */
    boolean isSymmetrical(TreeNode pRoot) {
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(pRoot);
        return judge(treeNodes);
    }
    boolean judge(List<TreeNode> list){
        List<TreeNode> newList = new ArrayList<>();
        int length = list.size();
        for (int i = 0;i<=length/2-1;i++){
            if (list.get(i)==null&&null==list.get(length-1-i)) continue;
            if ((list.get(i)==null&&null!=list.get(length-1-i))
                    ||(list.get(i)!=null&&null==list.get(length-1-i))){
                return false;
            }
            if (list.get(i).val!=list.get(length-1-i).val) return false;
        }
        for (int i =0;i<length;i++){
            if (list.get(i)!=null){
                newList.add(list.get(i).left);
                newList.add(list.get(i).right);
            }
        }
        if (newList.size()==0) return true;
        return judge(newList);
    }

    /**
     * 官方题解：递归判断，让构成对称节点去判断
     */
    public boolean jude(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        }

        if (node1.val != node2.val) {
            return false;
        } else {
            return jude(node1.left, node2.right) && jude(node1.right, node2.left);
        }
    }

    public boolean isSymmetrical1(TreeNode pRoot) {
        return pRoot==null || jude(pRoot.left, pRoot.right);
    }

}
