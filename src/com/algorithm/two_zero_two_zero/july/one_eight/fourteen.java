package com.algorithm.two_zero_two_zero.july.one_eight;

/**
   @autho*r Ming
   @date 2020/7/18 - 23:10
   @describe
  */
public class fourteen {

    /**
       输入一个链表，输出该链表中倒数第k个结点。
      */
    /**
        public class ListNode {
            int val;
            ListNode next = null;

            ListNode(int val) {
                this.val = val;
            }
        } */

    /**
       运行时间：13ms

       占用内存：9784k
       @param head
       @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        int number=0;
        while (head.next!=null) {
            number++;
        }
        ListNode listNode = head;
        int i = number - k;
        for (;i>0;i--){
            listNode = listNode.next;
        }
        return listNode;
    }

    /**
     * 快慢指针（网上代码）
     * 运行时间：14ms
     *
     * 占用内存：9568k
     *
     * 快指针先跑K个点，然后快慢指针再同时跑，当快指针到达最后时，他们之间间隔k个点，慢指针就是倒数第k个了
     * @param pListHead
     * @param k
     * @return
     */
    public ListNode FindKthToTail2(ListNode pListHead,  int k) {
        if (null == pListHead || k <= 0) {
            return null;
        }


        ListNode slow = pListHead, fast = pListHead;
        while (k!=0) {
            if (fast!=null) {
                fast = fast.next;
            } else {
                return null; //如果单链表长度 < K,直接返回
            }
            k--;
        }
        while (fast!=null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}
