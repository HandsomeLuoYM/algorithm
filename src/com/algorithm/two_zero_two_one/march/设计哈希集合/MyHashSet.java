package com.algorithm.two_zero_two_one.march.设计哈希集合;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Ming
 * @date 2021/3/13 - 10:37
 * @describe
 */
public class MyHashSet {
    /**
     * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
     * <p>
     * 实现 MyHashSet 类：
     * <p>
     * void add(key) 向哈希集合中插入值 key 。
     * bool contains(key) 返回哈希集合中是否存在这个值 key 。
     * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
     * <p>
     * 示例：
     * <p>
     * 输入：
     * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
     * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
     * 输出：
     * [null, null, null, true, false, null, true, null, false]
     * <p>
     * 解释：
     * MyHashSet myHashSet = new MyHashSet();
     * myHashSet.add(1);      // set = [1]
     * myHashSet.add(2);      // set = [1, 2]
     * myHashSet.contains(1); // 返回 True
     * myHashSet.contains(3); // 返回 False ，（未找到）
     * myHashSet.add(2);      // set = [1, 2]
     * myHashSet.contains(2); // 返回 True
     * myHashSet.remove(2);   // set = [1]
     * myHashSet.contains(2); // 返回 False ，（已移除）
     */

    private static final int BASE = 769;
    private LinkedList[] data;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return;
            }
        }
        data[h].offerLast(key);
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }


}
