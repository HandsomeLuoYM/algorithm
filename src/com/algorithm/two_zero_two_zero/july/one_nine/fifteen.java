package com.algorithm.two_zero_two_zero.july.one_nine;

import java.util.Collections;
import java.util.Vector;

/**
   @author Ming
   @date 2020/7/20 - 0:58
   @describe
  */
public class fifteen {

    /**
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
} */

    /**
       输入一个链表，反转链表后，输出新链表的表头。
       运行时间：12ms

       占用内存：9692k
       @param head
       @return
      */
    public static ListNode ReverseList(ListNode head) {

        if(head==null) return null;
        ListNode newHead = null,temp,p;

        while(head!=null){
            temp = head;
            p = head.next;
            temp.next=newHead;
            newHead = temp;
            head = p;
        }
        return newHead;
    }

    /**
       使用容器进行反转
       @param pHead
       @return
      */
    ListNode ReverseList2(ListNode pHead) {
        if (pHead==null) return null;
        Vector<ListNode> v = new Vector<>();
        while (pHead!=null) {
            v.add(pHead);
            pHead = pHead.next;
        }
        Collections.swap(v, 0, v.size());// 反转vector，也可以逆向遍历
        ListNode head = v.get(0);
        ListNode cur = head;
        for (int i=1; i<v.size(); ++i) { // 构造链表
            cur.next = v.get(i); // 当前节点的下一个指针指向下一个节点
            cur = cur.next; // 当前节点后移
        }
        cur.next = null; // 切记最后一个节点的下一个指针指向nullptr
        return head;
    }

}
