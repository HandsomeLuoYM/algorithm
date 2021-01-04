package com.algorithm.two_zero_two_zero.august.zero_five;

import java.util.HashMap;
import java.util.Map;

/**
   @author Ming
   @date 2020/8/5 - 16:54
   @describe
  */
public class LeetCode {

    /**
       在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
       这个地区只有一个入口，我们称之为“根”。
       除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
       一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
       如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

       计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

     **/

    /**
       执行用时：1425 ms, 在所有 Java 提交中击败了5.03%的用户
       内存消耗：39.5 MB, 在所有 Java 提交中击败了57.32%的用户
       @param root
       @param flag
       @return
      */
    public int ca(TreeNode root,boolean flag){
        if (root==null) return 0;
        if (flag){
            return Math.max(root.val+ca(root.left,false) + ca(root.right,false),ca(root.left,true)+ca(root.right,true));
        }else {
            return ca(root.left,true)+ca(root.right,true);
        }
    }

    public int rob(TreeNode root) {
        if(root==null) return 0;
        return Math.max(ca(root.left,true)+ca(root.right,true),root.val+ca(root.left,false)+ca(root.right,false));
    }

    /////////////////////////////////////////------------优化------------//////////////////////////////////////////////////

    public int rob3(TreeNode root) {
        if (root == null) return 0;

        //选中
        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }

        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }

        return Math.max(money, rob(root.left) + rob(root.right));
    }

    /////////////////////////////////////////------------再次优化------------//////////////////////////////////////////////////

    /**
       解法二、记忆化 - 解决重复子问题
       针对解法一种速度太慢的问题，经过分析其实现，我们发现爷爷在计算自己能偷多少钱的时候，
       同时计算了 4 个孙子能偷多少钱，也计算了 2 个儿子能偷多少钱。这样在儿子当爷爷时，就会产生重复计算一遍孙子节点。

       于是乎我们发现了一个动态规划的关键优化点

       重复子问题

       我们这一步针对重复子问题进行优化，我们在做斐波那契数列时，使用的优化方案是记忆化，
       但是之前的问题都是使用数组解决的，把每次计算的结果都存起来，下次如果再来计算，就从缓存中取，
       不再计算了，这样就保证每个数字只计算一次。
       由于二叉树不适合拿数组当缓存，我们这次使用哈希表来存储结果，TreeNode 当做 key，能偷的钱当做 value

       @param root
       @return
      */
    public int rob4(TreeNode root) {
        //记录到该节点时的最大值
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return robInternal(root, memo);
    }

    public int robInternal(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int money = root.val;

        if (root.left != null) {
            money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
        }
        if (root.right != null) {
            money += (robInternal(root.right.left, memo) + robInternal(root.right.right, memo));
        }
        int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
        memo.put(root, result);
        return result;
    }

    /////////////////////////////////////////------------最终的优化------------//////////////////////////////////////////////////

    /**
       每个节点可选择偷或者不偷两种状态，根据题目意思，相连节点不能一起偷

       当前节点选择偷时，那么两个孩子节点就不能选择偷了
       当前节点选择不偷时，两个孩子节点只需要拿最多的钱出来就行(两个孩子节点偷不偷没关系)

       我们使用一个大小为 2 的数组来表示 int[] res = new int[2] 0 代表不偷，1 代表偷

       任何一个节点能偷到的最大钱的状态可以定义为

       当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
       当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数

      */
    public int rob5(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode root) {

        if (root == null) return new int[2];

        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }


    /////////////////////////////////////////------------官方解法------------//////////////////////////////////////////////////

    /**
      简化一下这个问题：一棵二叉树，树上的每个点都有对应的权值，每个点有两种状态（选中和不选中），
       问在不能同时选中有父子关系的点的情况下，能选中的点的最大权值和是多少。

       我们可以用 f(o)f(o) 表示选择 oo 节点的情况下，oo 节点的子树上被选择的节点的最大权值和；
       g(o)g(o) 表示不选择 oo 节点的情况下，oo 节点的子树上被选择的节点的最大权值和；ll 和 rr 代表 oo 的左右孩子。

       当 oo 被选中时，oo 的左右孩子都不能被选中，故 oo 被选中情况下子树上被选中点的最大权值和为 ll 和 rr 不被选中的最大权值和相加，
       即 f(o) = g(l) + g(r)f(o)=g(l)+g(r)。

       当 oo 不被选中时，oo 的左右孩子可以被选中，也可以不被选中。对于 oo 的某个具体的孩子 xx，
       它对 oo 的贡献是 xx 被选中和不被选中情况下权值和的较大值。
       故 g(o) = \max \{ f(l) , g(l)\}+\max\{ f(r) , g(r) \}g(o)=max{f(l),g(l)}+max{f(r),g(r)}。

       至此，我们可以用哈希映射来存 ff 和 gg 的函数值，用深度优先搜索的办法后序遍历这棵二叉树，
       我们就可以得到每一个节点的 ff 和 gg。根节点的 ff 和 gg 的最大值就是我们要找的答案。

      */
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

    public int rob2(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        //node 被选中时，到该节点他的最大值
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        //node 没有被选中，到该节点他的最大值
        g.put(
                node ,
                Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0))
                + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0))
        );
    }


}
