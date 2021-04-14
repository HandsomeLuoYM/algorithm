package com.algorithm.two_zero_two_one.february.反转链表II;

/**
 * @author Ming
 * @date 2021/2/10 - 10:00
 * @describe
 */
public class LeetCode {
    /**
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     * <p>
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     */
    /**
     * 思路：
     *      直接反转
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了80.94%的用户
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode newHead = new ListNode(-1, head), start = newHead, now, next, tail;
        //start 下一个节点就需要逆转了
        for (int i = 1; i < m; i++) {
            start = start.next;
        }
        tail = now = start.next;
        next = now.next;
        for (int i = m; i <= n; i++) {
            now.next = start.next;
            start.next = now;
            now = next;
            if(next != null) next = now.next;
        }
        tail.next = now;
        return newHead.next;
    }

    /**
     * 递归
     *      左右两边同时交换值
     */
    class Solution {

        // Object level variables since we need the changes
        // to persist across recursive calls and Java is pass by value.
        private boolean stop;
        private ListNode left;

        public void recurseAndReverse(ListNode right, int m, int n) {

            // base case. Don't proceed any further
            if (n == 1) {
                return;
            }

            // Keep moving the right pointer one step forward until (n == 1)
            right = right.next;

            // Keep moving left pointer to the right until we reach the proper node
            // from where the reversal is to start.
            if (m > 1) {
                this.left = this.left.next;
            }

            // Recurse with m and n reduced.
            this.recurseAndReverse(right, m - 1, n - 1);

            // In case both the pointers cross each other or become equal, we
            // stop i.e. don't swap data any further. We are done reversing at this
            // point.
            if (this.left == right || right.next == this.left) {
                this.stop = true;
            }

            // Until the boolean stop is false, swap data between the two pointers
            if (!this.stop) {
                int t = this.left.val;
                this.left.val = right.val;
                right.val = t;

                // Move left one step to the right.
                // The right pointer moves one step back via backtracking.
                this.left = this.left.next;
            }
        }

        public ListNode reverseBetween(ListNode head, int m, int n) {
            this.left = head;
            this.stop = false;
            this.recurseAndReverse(head, m, n);
            return head;
        }
    }

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

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        ListNode listNode = l.new ListNode(1);
        listNode.next = l.new ListNode(2);
        listNode.next.next = l.new ListNode(3);
        listNode.next.next.next = l.new ListNode(4);
        listNode.next.next.next.next = l.new ListNode(5);
        System.out.println(l.reverseBetween(listNode, 2, 4));

    }
}
