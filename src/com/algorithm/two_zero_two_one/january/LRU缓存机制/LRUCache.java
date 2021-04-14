package com.algorithm.two_zero_two_one.january.LRU缓存机制;

import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming
 * @date 2021/1/26 - 21:45
 * @describe
 */
public class LRUCache {

    /**
     * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
     * 实现 LRUCache 类：
     *
     * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，
     *      则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     *
     * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
     *
     * 输入
     * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
     * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
     * 输出
     * [null, null, null, 1, null, -1, null, -1, 3, 4]
     *
     * 解释
     * LRUCache lRUCache = new LRUCache(2);
     * lRUCache.put(1, 1); // 缓存是 {1=1}
     * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
     * lRUCache.get(1);    // 返回 1
     * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
     * lRUCache.get(2);    // 返回 -1 (未找到)
     * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
     * lRUCache.get(1);    // 返回 -1 (未找到)
     * lRUCache.get(3);    // 返回 3
     * lRUCache.get(4);    // 返回 4
     *
     * 1 <= capacity <= 3000
     * 0 <= key <= 3000
     * 0 <= value <= 104
     * 最多调用 3 * 104 次 get 和 put
     *
     */
    /**
     * 思路：（双向链表 + 哈希）
     *      设置一个map来存储是否存在，保证在get的时候，时间复杂度最低，然后利用双向链表来存储
     * 执行用时：17 ms, 在所有 Java 提交中击败了96.72%的用户
     * 内存消耗：46.8 MB, 在所有 Java 提交中击败了19.40%的用户
     */
    int capacity, number = 0;
    Node head, tail;
    Map<Integer, Node> map = new HashMap<Integer, Node>();
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(null, null, null, null);
        tail = new Node(null, null, null, head);
        head.next = tail;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        else {
            //调节位置
            node.pre.next = node.next;
            node.next.pre = node.pre;
            //插入到头
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
            return node.value;
        }
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            Node newNode = new Node(key, value, null, null);
            map.put(key, newNode);
            newNode.pre = head;
            newNode.next = head.next;
            head.next.pre = newNode;
            head.next = newNode;
            //容量满了，淘汰最后一个
            if (number == capacity) {
                map.remove(tail.pre.key);
                tail.pre = tail.pre.pre;
                tail.pre.next = tail;
            }else {  //容量没满
                number++;
            }

        }else {  //节点的 key 存在
            node.value = value;
            //改变前后节点，独立出节点node
            node.pre.next = node.next;
            node.next.pre = node.pre;
            //插入到头中
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }
    }


    class Node {
        Integer key;
        Integer value;
        Node next;
        Node pre;

        public Node(Integer key, Integer value, Node next, Node pre) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.pre = pre;
        }
    }
}
