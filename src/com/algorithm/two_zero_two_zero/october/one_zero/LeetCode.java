package com.algorithm.two_zero_two_zero.october.one_zero;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ming
 * @date 2020/10/10 - 9:35
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     *
     * 说明：不允许修改给定的链表。
     *
     */

    /**
     * hash表记录每个节点，当节点相同时即为入口
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();
        ListNode node = head;
        while (node!=null){
            if (set.contains(node)) return node;
            set.add(node);
            node = node.next;
        }
        return null;
    }

    /**
     * 快慢指针，当快指针相遇慢指针时即比慢指针多跑了慢指针的步数，即开始倒入口处的距离  +  入口处到相遇位置的距离（一倍距离），
     * 所以快慢指针以相同速度一起走，当相遇时即入口点
     */
    public ListNode detectCycle1(ListNode head) {
        if (head==null) return null;
        ListNode fast = head.next, slow=head;
        while (fast!=null){
            if (fast==slow) break;
            if (fast.next==null||fast.next.next==null) return null;
            fast=fast.next.next;
            slow=slow.next;
        }
        if(fast==null) return null;
        slow = head;
        fast = fast.next;
        while (slow!=fast){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
