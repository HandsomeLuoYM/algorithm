package com.algorithm.september.two_eight;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/9/28 - 15:13
 * @describe
 */
public class LeetCode {

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

    /**
     * 自己的解法：遍历每一层，用一个队列来存储，判断子节点是否为空，
     *             让其加入，当为当前层最后一个时，不让它下一个节点指向下一个，在遍历时，记录每一个节点
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了45.77%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了5.08%的用户
     */
    public Node connect(Node root) {
        if (root==null) return null;
        Queue<Node> queue = new LinkedList<>();
        Node p;
        int number;
        queue.add(root);
        while (!queue.isEmpty()){
            number = queue.size();
            for (int i = 0 ; i<number ; i++){
                p = queue.poll();
                if (i!=number-1) p.next = queue.peek();
                if (p.left!=null) queue.add(p.left);
                if (p.right!=null) queue.add(p.right);
            }
        }
        return root;
    }

    /**
     * 因为必须处理树上的所有节点，所以无法降低时间复杂度，但是可以尝试降低空间复杂度。
     *
     * 在方法一中，因为对树的结构一无所知，所以使用队列保证有序访问同一层的所有节点，并建立它们之间的连接。
     * 然而不难发现：一旦在某层的节点之间建立了 next 指针，那这层节点实际上形成了一个链表。
     * 因此，如果先去建立某一层的 next 指针，再去遍历这一层，就无需再使用队列了。
     *
     * 基于该想法，提出降低空间复杂度的思路：如果第 i 层节点之间已经建立 next 指针，
     * 就可以通过 next 指针访问该层的所有节点，同时对于每个第 i 层的节点，
     * 我们又可以通过它的 left 和 right 指针知道其第 i+1 层的孩子节点是什么，
     * 所以遍历过程中就能够按顺序为第 i+1 层节点建立 next 指针。
     *
     */
    class Solution {
        Node last = null, nextStart = null;

        public Node connect(Node root) {
            if (root == null) {
                return null;
            }
            Node start = root;
            while (start != null) {
                last = null;
                nextStart = null;
                for (Node p = start; p != null; p = p.next) {
                    if (p.left != null) {
                        handle(p.left);
                    }
                    if (p.right != null) {
                        handle(p.right);
                    }
                }
                start = nextStart;
            }
            return root;
        }

        public void handle(Node p) {
            if (last != null) {
                last.next = p;
            }
            if (nextStart == null) {
                nextStart = p;
            }
            last = p;
        }
    }

}
