package com.algorithm.two_zero_two_one.march.扁平化嵌套列表迭代器;

import java.util.Iterator;

/**
 * @author Ming
 * @date 2021/3/23 - 9:53
 * @describe
 */
public class NestedIterator implements Iterator<Integer> {
    /**
     * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
     * <p>
     * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: [[1,1],2,[1,1]]
     * 输出: [1,1,2,1,1]
     * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
     * 示例 2:
     * <p>
     * 输入: [1,[4,[6]]]
     * 输出: [1,4,6]
     * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
     */
    // 存储列表的当前遍历位置
//    private Deque<Iterator<NestedInteger>> stack;

//    public NestedIterator(List<NestedInteger> nestedList) {
//        stack = new LinkedList<Iterator<NestedInteger>>();
//        stack.push(nestedList.iterator());
//    }

    @Override
    public Integer next() {
        // 由于保证调用 next 之前会调用 hasNext，直接返回栈顶列表的当前元素
//        return stack.peek().next().getInteger();
        return 1;
    }

    @Override
    public boolean hasNext() {
//        while (!stack.isEmpty()) {
//            Iterator<NestedInteger> it = stack.peek();
//            if (!it.hasNext()) { // 遍历到当前列表末尾，出栈
//                stack.pop();
//                continue;
//            }
//            // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
//            NestedInteger nest = it.next();
//            if (nest.isInteger()) {
//                List<NestedInteger> list = new ArrayList<NestedInteger>();
//                list.add(nest);
//                stack.push(list.iterator());
//                return true;
//            }
//            stack.push(nest.getList().iterator());
//        }
        return false;
    }


}
