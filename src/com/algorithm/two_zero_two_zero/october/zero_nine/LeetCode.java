package com.algorithm.two_zero_two_zero.october.zero_nine;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ming
 * @date 2020/10/9 - 9:16
 * @describe
 */
public class LeetCode {

    /**
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     */

    /**
     * 自己的思路：快慢指针来搜索
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了85.15%的用户
     */
    public boolean hasCycle(ListNode head) {
        if (head==null) return false;
        ListNode fast = head.next, slow=head;
        while (fast!=null){
            if (fast==slow) return true;
            if (fast.next==null||fast.next.next==null) return false;
            fast=fast.next.next;
            slow=slow.next;
        }
        return false;
    }

    public boolean hasCycle1(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();
        ListNode node = head;
        while (node!=null){
            if (set.contains(node)) return true;
            set.add(node);
            node = node.next;
        }
        return false;
    }

}
