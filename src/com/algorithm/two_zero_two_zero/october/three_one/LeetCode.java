package com.algorithm.two_zero_two_zero.october.three_one;

import java.util.*;

/**
 * @author Ming
 * @date 2020/10/31 - 12:27
 * @describe
 */
public class LeetCode {

    /**
     * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
     *
     * 注意: 允许出现重复元素。
     *
     * insert(val)：向集合中插入元素 val。
     * remove(val)：当 val 存在时，从集合中移除一个 val。
     * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
     *
     * // 初始化一个空的集合。
     * RandomizedCollection collection = new RandomizedCollection();
     *
     * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
     * collection.insert(1);
     *
     * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
     * collection.insert(1);
     *
     * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
     * collection.insert(2);
     *
     * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
     * collection.getRandom();
     *
     * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
     * collection.remove(1);
     *
     * // getRandom 应有相同概率返回 1 和 2 。
     * collection.getRandom();
     *
     */

    /**
     * 官方题解
     * 执行用时：14 ms, 在所有 Java 提交中击败了91.73%的用户
     * 内存消耗：45.4 MB, 在所有 Java 提交中击败了53.55%的用户
     */
    class RandomizedCollection {
        Map<Integer, Set<Integer>> idx;//存放索引
        List<Integer> nums;//存放数据

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            idx = new HashMap<Integer, Set<Integer>>();
            nums = new ArrayList<Integer>();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            nums.add(val);
            Set<Integer> set = idx.getOrDefault(val, new HashSet<Integer>());
            set.add(nums.size() - 1);
            idx.put(val, set);
            return set.size() == 1;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if (!idx.containsKey(val)) {
                return false;
            }
            Iterator<Integer> it = idx.get(val).iterator();//获取迭代器
            int i = it.next();//获得第一个节点索引
            int lastNum = nums.get(nums.size() - 1);//获取最后一个元素，和其替换
            nums.set(i, lastNum);//替换
            idx.get(val).remove(i);//索引表去除掉第一个元素位置
            idx.get(lastNum).remove(nums.size() - 1);//最后的元素表也去掉第一个位置
            if (i < nums.size() - 1) {
                idx.get(lastNum).add(i);//lastNum位置修改为i位置
            }
            if (idx.get(val).size() == 0) {
                idx.remove(val);//索引表位置没有参数则去掉set
            }
            nums.remove(nums.size() - 1);//去除List最后一个元素
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return nums.get((int) (Math.random() * nums.size()));
        }
    }

    class RandomizedCollection1 {
        int n ;//当前集合大小
        HashMap<Integer,Set<Integer>>map;
        ArrayList<Integer>list;
        Random random;
        /** Initialize your data structure here. */
        public RandomizedCollection1() {
            this.random = new Random();
            this.map = new HashMap();
            this.n = 0;
            this.list = new ArrayList<>();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            Set set = map.get(val);
            if(set==null)   set = new HashSet<>();
            set.add(n);//添加索引
            list.add(val);
            map.put(val, set);
            n++;
            return set.size()==1;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if(map.containsKey(val)){
                int lastIndex = n-1;//得到最后2个值索引
                Set lastset = map.get(list.get(lastIndex));
                Set set = map.get(val);
                int currIndex = (int)set.iterator().next();//得到当前值索引
                //进行删除操作
                swap(list, currIndex, lastIndex);
                list.remove(n-1);//将其在列表中删除
                set.remove(currIndex);//删除原值
                if(set.size()==0)   map.remove(val);//在图中删除
                //修改最后一个值的索引
                lastset.remove(n-1);
                lastset.add(currIndex);
                n--;
            }else{
                return false;
            }
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return list.get(random.nextInt(n));
        }
        private void swap(List<Integer> list ,int i,int j){
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    /**
     * Your RandomizedCollection object will be instantiated and called as such:
     * RandomizedCollection obj = new RandomizedCollection();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
}
