package com.algorithm.july.two_zero;

/**
   @author Ming
   @date 2020/7/21 - 0:51
   @describe
  */
public class seventeen {


    /**

     输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）

     public class TreeNode {
         int val = 0;
         TreeNode left = null;
         TreeNode right = null;

         public TreeNode(int val) {
         this.val = val;
         }
     }
      */

    /**
       运行时间：14ms

       占用内存：9468k
       @param root1
       @param root2
       @return
      */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1==null || root2==null) return false;
        if (judge(root1,root2)) return true;
        else return HasSubtree(root1.left ,root2) || HasSubtree( root1.right, root2);
    }

    private boolean judge(TreeNode root1,TreeNode root2){
        if (root2==null ) return true;
        if (root1!=null && root2.val==root1.val) return judge(root1.left ,root2.left) && judge( root1.right, root2.right);
        else return false;
    }


}
