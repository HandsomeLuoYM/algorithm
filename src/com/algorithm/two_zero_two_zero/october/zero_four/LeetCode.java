package com.algorithm.two_zero_two_zero.october.zero_four;

/**
 * @author Ming
 * @date 2020/10/4 - 13:34
 * @describe
 */
public class LeetCode {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。
     * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */

    /**
     * 思路：递归判断节点数据，添加
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.91%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了29.35%的用户
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode();
        add(root,l1,l2,0);
        return root.next;
    }
    ListNode node1,node2;
    private void add(ListNode node, ListNode l1, ListNode l2, int num){
        if (l1==null&&l2==null&&num==0) return;
        int num1 = l1==null ? 0 : l1.val;
        int num2 = l2==null ? 0 : l2.val;
        node1 = l1==null ? null : l1.next;
        node2 = l2==null ? null : l2.next;
        node.next = new ListNode((num+num1+num2)%10);
        add(node.next, node1, node2, (num+num1+num2)/10);
    }

    /**
     * 官方题解
     * 模拟
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = null, tail = null;
            int carry = 0;
            while (l1 != null || l2 != null) {
                int n1 = l1 != null ? l1.val : 0;
                int n2 = l2 != null ? l2.val : 0;
                int sum = n1 + n2 + carry;
                if (head == null) {
                    head = tail = new ListNode(sum % 10);
                } else {
                    tail.next = new ListNode(sum % 10);
                    tail = tail.next;
                }
                carry = sum / 10;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            if (carry > 0) {
                tail.next = new ListNode(carry);
            }
            return head;
        }
    }

}
