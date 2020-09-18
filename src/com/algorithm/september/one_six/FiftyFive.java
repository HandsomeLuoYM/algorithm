package com.algorithm.september.one_six;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ming
 * @date 2020/9/16 - 22:21
 * @describe
 */
public class FiftyFive {

    /**
     * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
     */

    /**
     * 思路：设置快慢指针，如果快慢指针相遇，则证明快指针比慢指针多走了循环里的n圈，
     *       也就是这n圈乘以一圈个数等于从起点到他们的相遇点，（k乘以一圈个数）-（入口点到相遇点的距离）=起点到结点距离，
     *       所以只需要再设置一个指针，与慢指针一起走，当相遇时就是入口了
     * 运行时间：13ms
     *
     * 占用内存：9544k
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if(pHead == null || pHead.next == null){
            return null;
        }
        ListNode fast=pHead ,slow=pHead;
        while (fast!=null&&slow!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (slow==fast){
                ListNode slow2 = pHead;
                while (slow!=slow2){
                    slow=slow.next;
                    slow2=slow2.next;
                }
                return slow2;
            }
        }
        return null;
    }

    /**
     * 思路：用HashSet来存放每个节点，当遍历后为空时Set都没有重复的则表示没有重复
     * 运行时间：12ms
     *
     * 占用内存：9740k
     */
    public ListNode EntryNodeOfLoop1(ListNode pHead) {
        Set<ListNode> set = new HashSet();
        while (pHead!=null) {
            if (!set.contains(pHead)) {
                set.add(pHead);
                pHead = pHead.next;
            } else {
                return pHead;
            }
        }
        return null;
    }

}
