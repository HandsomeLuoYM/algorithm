package com.algorithm.two_zero_two_one.march.验证二叉树的前序序列化;

/**
 * @author Ming
 * @date 2021/3/12 - 15:21
 * @describe
 */
public class LeetCode {
    /**
     * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
     *
     *      _9_
     *     /   \
     *    3     2
     *   / \   / \
     *  4   1  #  6
     * / \ / \   / \
     * # # # #   # #
     * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
     *
     * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
     *
     * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
     *
     * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
     *
     * 示例 1:
     *
     * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
     * 输出: true
     * 示例 2:
     *
     * 输入: "1,#"
     * 输出: false
     *
     */
    /**
     * 数字游戏
     * 执行用时：3 ms, 在所有 Java 提交中击败了82.69%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了98.96%的用户
     */
    public boolean isValidSerialization(String preorder) {
        int length = preorder.length(), number = 0, other = 0, i = 0;;
        while (i < length) {
            if(preorder.charAt(i) == '#') {
                other++;
                i++;
            } else if (preorder.charAt(i) <= '9' && preorder.charAt(i) >= '0') {
                i++;
                //找到数字
                while (i < length && preorder.charAt(i) <= '9' && preorder.charAt(i) >= '0') i++;
                number++;
            }else {
                i++;
            }
            if (i != length && number + 1 <= other)
                return false;
        }

        if(number + 1 == other)
            return true;
        else
            return false;
    }


    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        System.out.println(l.isValidSerialization("1"));
    }
}
