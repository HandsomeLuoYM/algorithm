package com.algorithm.two_zero_two_zero.september.two_two;

/**
 * @author Ming
 * @date 2020/9/22 - 14:09
 * @describe 968. 监控二叉树
 */
public class LeetCode {

    /**
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     *
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     *
     * 计算监控树的所有节点所需的最小摄像头数量。
     */

    /**
     * 本题以二叉树为背景，不难想到用递归的方式求解。本题的难度在于如何从左、右子树的状态，推导出父节点的状态。
     *
     * 为了表述方便，我们约定：如果某棵树的所有节点都被监控，则称该树被「覆盖」。
     *
     * 假设当前节点为 root，其左右孩子为 left,right。如果要覆盖以 root 为根的树，有两种情况：
     *      若在 root 处安放摄像头，则孩子 left,right 一定也会被监控到。此时，只需要保证 left 的两棵子树被覆盖，同时保证 right 的两棵子树也被覆盖即可。
     *      否则， 如果 root 处不安放摄像头，则除了覆盖 root 的两棵子树之外，孩子 left,right 之一必须要安装摄像头，从而保证root 会被监控到。
     *
     *
     * 根据上面的讨论，能够分析出，对于每个节点 root ，需要维护三种类型的状态：
         * 状态 a：root 必须放置摄像头的情况下，覆盖整棵树需要的摄像头数目。
         * 状态 b：覆盖整棵树需要的摄像头数目，无论 root 是否放置摄像头。
         * 状态 c：覆盖两棵子树需要的摄像头数目，无论节点 root 本身是否被监控到。
     *
     */
    class Solution {
        public int minCameraCover(TreeNode root) {
            int[] array = dfs(root);
            return array[1];
        }

        public int[] dfs(TreeNode root) {
            if (root == null) {
                return new int[]{Integer.MAX_VALUE / 2, 0, 0};
            }
            int[] leftArray = dfs(root.left);
            int[] rightArray = dfs(root.right);
            int[] array = new int[3];
            //当前节点要有相机，所以无论左右节点是否被覆盖都行
            array[0] = leftArray[2] + rightArray[2] + 1;
            //左边放置了，或者右边放置了的最小和自己放置了的最小比较，要求目前最小
            array[1] = Math.min(array[0], Math.min(leftArray[0] + rightArray[1], rightArray[0] + leftArray[1]));
            //自身不知道会不会被监控，这个要求父节点要监控它
            array[2] = Math.min(array[0], leftArray[1] + rightArray[1]);
            return array;
        }
    }

    /**
     *
     * 这题正常的逻辑是从上往下，但我们还可以逆向思维，从下往上来统计。那么一个节点就会有3种情况
     *      1，当前节点有相机
     *      2，当前节点不需要相机（子节点有相机把它给覆盖了）
     *      3，当前节点没有相机并且也没有被子节点给覆盖（那么他只能等他的父节点把它给覆盖了）
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了61.88%的用户
     */
    //NO_CAMERA表示的是子节点没有相机，当前节点也没放相机
    private final int NO_CAMERA = 0;
    //HAS_CAMERA表示当前节点有一个相机
    private final int HAS_CAMERA = 1;
    //NO_NEEDED表示当前节点没有相机，但他的子节点有一个相机，把它给
    //覆盖了，所以它不需要了。或者他是一个空的节点也是不需要相机的
    private final int NO_NEEDED = 2;

    //全局的，统计有多少相机
    int res = 0;

    public int minCameraCover(TreeNode root) {
        //边界条件判断
        if (root == null)
            return 0;
        //如果最后返回的是NO_CAMERA，表示root节点的子节点也没有相机，
        //所以root节点要添加一个相机
        if (dfs(root) == NO_CAMERA)
            res++;
        //返回结果
        return res;
    }

    public int dfs(TreeNode root) {
        //如果是空的，就不需要相机了
        if (root == null)
            return NO_NEEDED;
        int left = dfs(root.left), right = dfs(root.right);
        //如果左右子节点有一个是NO_CAMERA，表示的是子节点既没相机，也没相机覆盖它，
        //所以当前节点需要有一个相机
        if (left == NO_CAMERA || right == NO_CAMERA) {
            //在当前节点放一个相机，统计相机的个数
            res++;
            return HAS_CAMERA;
        }
        //如果左右子节点只要有一个有相机，那么当前节点就不需要相机了，否则返回一个没有相机的标记
        return left == HAS_CAMERA || right == HAS_CAMERA ? NO_NEEDED : NO_CAMERA;
    }

}
