package com.algorithm.two_zero_two_zero.august.three_zero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ming
 * @date 2020/8/30 - 13:41
 * @describe
 */
public class twenty_five {

    /**
     *
     * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
     * 另一个特殊指针random指向一个随机节点），请对此链表进行深拷贝，并返回拷贝后的头结点。
     * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
     */
    /**
     * 运行时间：14ms
     *
     * 占用内存：9796k
     */
    public RandomListNode Clone(RandomListNode pHead){
        if (pHead==null) return null;
        //复制链表
        Map<Integer, List<RandomListNode>> map = new HashMap<>();
        RandomListNode root = new RandomListNode(pHead.label),
                now=root,pnext=pHead.next,ppre=pHead;


        while (ppre!=null) {
            if (pnext!=null){
                now.next = new RandomListNode(pnext.label);
            }

            if (ppre.random!=null&&map.containsKey(ppre.random.label)){
                map.get(ppre.label).add(now);
            }else if (ppre.random!=null){
                ArrayList<RandomListNode> randomListNodes = new ArrayList<>();
                randomListNodes.add(now);
                map.put(ppre.random.label,randomListNodes);
            }

            ppre = pnext;
            if (pnext!=null){
                pnext=pnext.next;
            }
            now=now.next;
        }
        now = root;
        //构造新的队列
        while (null!=now){
            if (map.containsKey(now.label)){
                for (RandomListNode randomListNode : map.get(now.label)){
                    randomListNode.random=now;
                }
            }
            now=now.next;
        }

        return root;
    }


    /**
     * 别人的做法
     */
    //下面那段代码思维太混乱了，大家不要参考，如果要用map解决此题，看这段代码就好
    public class Solution {
        public RandomListNode Clone(RandomListNode pHead)
        {
            HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
            RandomListNode p = pHead;
            //第一次遍历 新建立节点
            while(p != null){
                RandomListNode newNode = new RandomListNode(p.label);
                map.put(p, newNode);
                p = p.next;
            }
            //第二次遍历 赋值映射关系
            p = pHead;
            while(p != null){
                RandomListNode node = map.get(p);
                node.next = (p.next == null)?null: map.get(p.next);
                node.random = (p.random == null)?null: map.get(p.random);
                p = p.next;
            }
            //最后的返回值
            return map.get(pHead);

        }
    }

    /*
     *解题思路：
     *1、遍历链表，复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
     *2、重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
     *3、拆分链表，将链表拆分为原链表和复制后的链表
     */
    public class Solution1 {
        public RandomListNode Clone(RandomListNode pHead) {
            if(pHead == null) {
                return null;
            }

            RandomListNode currentNode = pHead;
            //1、复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
            while(currentNode != null){
                RandomListNode cloneNode = new RandomListNode(currentNode.label);
                RandomListNode nextNode = currentNode.next;
                currentNode.next = cloneNode;
                cloneNode.next = nextNode;
                currentNode = nextNode;
            }

            currentNode = pHead;
            //2、重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
            while(currentNode != null) {
                currentNode.next.random = currentNode.random==null?null:currentNode.random.next;
                currentNode = currentNode.next.next;
            }

            //3、拆分链表，将链表拆分为原链表和复制后的链表
            currentNode = pHead;
            RandomListNode pCloneHead = pHead.next;
            while(currentNode != null) {
                RandomListNode cloneNode = currentNode.next;
                currentNode.next = cloneNode.next;
                cloneNode.next = cloneNode.next==null?null:cloneNode.next.next;
                currentNode = currentNode.next;
            }

            return pCloneHead;
        }
    }
}
