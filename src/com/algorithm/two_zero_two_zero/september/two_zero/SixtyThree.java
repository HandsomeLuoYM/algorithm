package com.algorithm.two_zero_two_zero.september.two_zero;

import java.util.*;

/**
 * @author Ming
 * @date 2020/9/20 - 11:47
 * @describe
 */
public class SixtyThree {

    /**
     * 自己的做法：先添加，在排序，最后求值
     * 运行时间：15ms
     *
     * 占用内存：9856k
     */
    List<Integer> list = new ArrayList<>();
    public void Insert(Integer num) {
        list.add(num);
    }

    public Double GetMedian() {
        Collections.sort(list);
        int index = list.size()/2;
        if (list.size()%2==1){
            return (double)list.get(list.size()/2);
        }else {
            return ((double)(list.get(index-1))+(double)list.get(index))/2;
        }
    }

    /**
     * 对应方法2，插入的时候排序，所以很容易想到利用堆排序，每次加入一个元素就调整堆。
     * 因为第4种使用到了堆，所以这里选择（在网上扒了）另一种插入排序的方法，二分法。
     */
    public class Solution1 {
        private List<Integer> list = new LinkedList();

        public void Insert(Integer num) {
            if(list.size()==0){
                list.add(num);
                return;
            }
            int first = 0;
            int last = list.size()-1;
            int mid = 0;
            while(first <= last){
                mid = (first+last)/2;
                if(list.get(mid)>num)
                    last = mid-1;
                else
                    first = mid+1;
            }
            list.add(first,num);
            return;
        }

        public Double GetMedian() {
            int index = list.size();
            if(index%2==1){
                return (double)list.get(index/2);
            }
            return ((double)(list.get(index/2-1))+(double)list.get(index/2))/2;
        }
    }

    /**
     * 使用树。让树给我们排序，然后我们再取中位数，但是值得注意的是，在Set集合中，
     * 没有get方法，所以无法直接获取某个角标所对应的元素，通过查资料，
     * 发现需要将Set转换成List即可，因此需要再处理一次。
     */
    public class Solution2 {
        public TreeSet<Integer> tree = new TreeSet<>();

        public void Insert(Integer num) {
            tree.add(num);
        }

        public Double GetMedian() {
            ArrayList<Integer> list = new ArrayList<>();
            list.addAll(tree);
            int index = list.size();
            if(index%2==1){
                return (double)list.get(index/2);
            }
            return ((double)(list.get(index/2-1))+(double)list.get(index/2))/2;
        }
    }

    /**
     * 将读入的数据分为几乎数量相同的两部分，一部分数字小，另一部分大。小
     * 的一部分采用大顶堆存放，大的一部分采用小顶堆存放。这样两个堆的堆顶就是整个数据流中，
     * 最中间的两个数。当总个数为偶数时，使两个堆的数目相同，则中位数=大顶堆的最大数字与小顶堆的最小数字的平均值；
     * 而总个数为奇数时，使小顶堆的个数比大顶堆多一，则中位数=小顶堆的最小数字。
     *
     * 插入的步骤如下：
     * 　　1.若已读取的个数为偶数（包括0）时，两个堆的数目已经相同，
     *       再插入一个数时，应该选一个数插入到小顶堆中，从而实现小顶堆的个数多一。
     *       但是，不能直接插到小顶堆，本应该选择一个数加入到小顶堆中，但是必须选一个较大的数放入小顶堆，
     *       而插入的这个数不一定符合要求（大顶堆的数不服它），
     *       所以这个数要和大顶堆的最大数（先进大顶堆）打一群架，谁赢了（谁大）谁进小顶堆。
     * 　　2。若已读取的个数为奇数时，小顶堆的个数多一，所以要将某个数字插入到大顶堆中，
     *      此时方法与上面类似。新进来的数要和小顶堆的堆顶（最小值）打一架，打输的（更小的那个数）进入大顶堆。
     *
     *   本方法的空间复杂度是O(1)，空间复杂度是O(logn)，相比于以上几个方法，可以说是最优选择。
     *   因此也是大家使用最多的解法。堆有多种方式实现，数组或者基于队列实现。这里使用PriorityQueue实现，
     *   PriorityQueue默认是一个小顶堆，因此我们需要自己实现大顶堆，这里传入自定义的Comparator函数可以实现大顶堆
     */
    public class Solution {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); //小顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>(){
            //大顶堆
            @Override
            public int compare(Integer i1,Integer i2){
                return i2-i1;//降序排列，小顶堆中是i1-i2
            }
        });
//Lambda表达式写法：
//PriorityQueue<Integer> Heap=new PriorityQueue<>((Comparator<Integer>)(o1,o2)->o2-o1);

        int count = 0;//记录当前个数是奇数还是偶数
        public void Insert(Integer num) {
            //个数为偶数的话，则先插入到大顶堆，并调整，然后将大顶堆中最大的数插入小顶堆中
            if(count % 2 == 0){
                maxHeap.offer(num);
                int max = maxHeap.poll();
                minHeap.offer(max);
            }else{
                //个数为奇数的话，则先插入到小顶堆，然后将小顶堆中最小的数插入大顶堆中
                minHeap.offer(num);
                int min = minHeap.poll();
                maxHeap.offer(min);
            }
            count++;
        }

        public Double GetMedian() {
            //当前为偶数个，则取小顶堆和大顶堆的堆顶元素求平均
            if(count % 2 == 0){
                return new Double(minHeap.peek() + maxHeap.peek())/2;
            }else{
                //当前为奇数个，则直接从小顶堆中取元素即可，所以我们要保证小顶堆中的元素的个数。
                return ((double)minHeap.peek());
            }
        }
    }
}
