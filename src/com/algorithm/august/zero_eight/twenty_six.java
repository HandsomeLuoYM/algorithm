package com.algorithm.august.zero_eight;

import java.util.ArrayList;

/**
   @author Ming
   @date 2020/8/8 - 18:15
   @describe
  */
public class twenty_six {


    /**
       中序遍历
       需要存放跟节点，否则返回的是中心点，不是最左或者最右点
       运行时间：12ms

       占用内存：9348k
      */
    TreeNode pre=null,root=null;

    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        Convert(pRootOfTree.left);

        if (root==null){
            root=pRootOfTree;
        }
        if (pre != null){
            pRootOfTree.left = pre;
            pre.right = pRootOfTree;
        }
        pre = pRootOfTree;

        Convert(pRootOfTree.right);

        return root;
    }
    ////////////////////////////////////////////////////

    /**
       倒中序遍历，与左序类似
      */
    public TreeNode Convert2(TreeNode pRootOfTree) {
        if (pRootOfTree==null)
            return null;
        Convert2(pRootOfTree.right);
        if (pre!= null){
            pRootOfTree.right=pre;
            pre.left=pRootOfTree;
        }
        pre=pRootOfTree;
        Convert2(pRootOfTree.left);
        //返回的为最左边的，不需要设置根节点
        return pre;
    }

    /////////////////////////////////////////////////

    /**
       首先最容易想到的，是用一个数组来存储中序遍历的节点，然后再从头到尾，建立节点前后的连接关系。
       @param pRootOfTree
       @return
      */
    public TreeNode Convert1(TreeNode pRootOfTree) {
        if (pRootOfTree==null)
            return null;
        ArrayList list;
        TreeNode gt=new TreeNode();
        list=new ArrayList<TreeNode>();
        Convert(list,pRootOfTree);
        return Convert(list,gt);
    }

    public TreeNode Convert(ArrayList<TreeNode> list,TreeNode gt){
        TreeNode head=list.get(0);
        TreeNode cur=head;
        for (int i=1;i<list.size();++i){
            TreeNode node=list.get(i);
            node.left=cur;
            cur.right=node;
            cur=node;
        }
        return head;
    }



}

