package com.algorithm.october.one_five;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/10/15 - 0:41
 * @describe
 */
public class LeetCode {
    /**
     * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下
     *
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * 初始状态下，所有 next 指针都被设置为 NULL
     *
     *
     */

    /**
     * 自己的思路：
     *      设置一个队列来记录每一层的个数，在每次遍历时记录该层的个数
     * 执行用时：3 ms, 在所有 Java 提交中击败了45.69%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了99.78%的用户
     */
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null || root.left == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int number;
        Node nowNode;
        while (!queue.isEmpty()){
            number = queue.size();
            while (number>1) {
                nowNode = queue.poll();
                nowNode.next = queue.peek();
                if (nowNode.left!=null) queue.add(nowNode.left);
                if (nowNode.right!=null) queue.add(nowNode.right);
                number--;
            }
            nowNode = queue.poll();
            if (nowNode.left!=null) queue.add(nowNode.left);
            if (nowNode.right!=null) queue.add(nowNode.right);        }
        return root;
    }

    /**
     * 自己的思路：
     *      利用自己的next函数
     * 执行用时：1 ms, 在所有 Java 提交中击败了59.77%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了81.18%的用户
     */
    public Node connect1(Node root) {
        if (root == null || root.left == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int number;
        Node nowNode;
        while (!queue.isEmpty()){
            number = queue.size();
            while (number>1) {
                nowNode = queue.poll();
                nowNode.left.next = nowNode.right;
                nowNode.right.next = nowNode.next.left;
                queue.add(nowNode.left);
                queue.add(nowNode.right);
                number--;
            }
            nowNode = queue.poll();
            nowNode.left.next = nowNode.right;
            if (nowNode.left.left==null) return root;
            queue.add(nowNode.left);
            queue.add(nowNode.right);
        }
        return root;
    }

    /**
     * 官方题解二：使用自己已经建立的next节点
     */
    class Solution {
        public Node connect(Node root) {
            if (root == null) {
                return root;
            }

            // 从根节点开始
            Node leftmost = root;

            while (leftmost.left != null) {

                // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
                Node head = leftmost;

                while (head != null) {

                    // CONNECTION 1
                    head.left.next = head.right;

                    // CONNECTION 2
                    if (head.next != null) {
                        head.right.next = head.next.left;
                    }

                    // 指针向后移动
                    head = head.next;
                }

                // 去下一层的最左的节点
                leftmost = leftmost.left;
            }

            return root;
        }
    }


}
