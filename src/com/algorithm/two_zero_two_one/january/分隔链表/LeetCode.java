package com.algorithm.two_zero_two_one.january.分隔链表;

/**
 * @author Ming
 * @date 2021/1/3 - 0:33
 * @describe
 */
public class LeetCode {


    /**
     * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
     * <p>
     * 你应当保留两个分区中每个节点的初始相对位置。
     * <p>
     * 输入：head = 1->4->3->2->5->2, x = 3
     * 输出：1->2->2->4->3->5
     */
    /**
     * 自己的思路：
     *      边遍历边修改，需要贤去掉前面所有小于 x 的节点
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.5 MB, 在所有 Java 提交中击败了94.52%的用户
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode now = head, index =  new ListNode(-1), prev = index;
        ListNode newHead = index;
        index.next = head;
        while (now != null && now.val < x) {
            prev = now;
            now = now.next;
            index = prev;
        }
        while (now != null) {
            if (now.val < x){
                prev.next = now.next;
                now.next = index.next;
                index.next = now;
                index = index.next;

                now = prev.next;
                continue;
            }
            prev = now;
            now = now.next;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();

        ListNode head = l.new ListNode(1);
        head.next = l.new ListNode(4);
        head.next.next = l.new ListNode(3);
        head.next.next.next = l.new ListNode(2);
        head.next.next.next.next = l.new ListNode(5);
        head.next.next.next.next.next = l.new ListNode(2);
        l.partition(head,3);
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
