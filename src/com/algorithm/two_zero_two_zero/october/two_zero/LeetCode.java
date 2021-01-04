package com.algorithm.two_zero_two_zero.october.two_zero;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/10/20 - 0:58
 * @describe 143. 重排链表
 */
public class LeetCode {

    /**
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     * 示例 1:
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     * <p>
     * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     */
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
     * 自己的做法：
     *      遍历整个链表，将他们加入到list中，如何再遍历一半去插入
     * 执行用时：3 ms, 在所有 Java 提交中击败了36.47%的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了99.97%的用户
     */
    public void reorderList(ListNode head) {
        if(head ==null || head.next ==null) return;
        List<ListNode> list = new ArrayList<>();
        ListNode node = head, tailNode;
        while (node != null){
            list.add(node);
            node = node.next;
        }
        int half = list.size()/2, length = list.size();
        node = list.get(0);
        for (int i =0; i<half; i++){
            tailNode = list.get(length-1-i);
            tailNode.next = node.next;
            node.next = tailNode;
            node = node.next.next;
        }
        list.get(half).next = null;
    }

    /**
     * 自己+题解的思路：
     *      先用快慢指针来寻找中间点，再将后半段倒置，再将倒置的后半段插入到前半段中
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了98.79%的用户
     */
    public void reorderList1(ListNode head) {
        if(head ==null || head.next ==null) return;
        ListNode fast=head.next,slow=head;
        //找到中间节点
        while (true){
            if (fast.next==null || fast.next.next==null) break;
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode half = slow.next, node = half.next, temp;
        slow.next=null;
        half.next=null;
        //倒逆后面链表
        while (node!=null){
            temp = node;
            node=node.next;
            temp.next = half;
            half = temp;
        }
        ListNode nowNode = head;
        while (nowNode!=null){
            temp = half;
            half=half.next;
            temp.next = nowNode.next;
            nowNode.next=temp;
            if (nowNode.next.next==null) {
                nowNode=nowNode.next;
                break;
            }
            nowNode=nowNode.next.next;
        }
        if (half!=null) nowNode.next=half;

    }
}
