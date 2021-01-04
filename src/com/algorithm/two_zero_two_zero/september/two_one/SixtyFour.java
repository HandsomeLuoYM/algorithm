package com.algorithm.two_zero_two_zero.september.two_one;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author Ming
 * @date 2020/9/21 - 16:00
 * @describe
 */
public class SixtyFour {
    /**
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
     * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
     * 他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
     * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     * 窗口大于数组长度的时候，返回空
     */

    /**
     * 自己的做法，利用TreeSet的数据结构（存放大顶堆），每一次都在里面更新，比暴力解法好
     * 运行时间：12ms
     *
     * 占用内存：9444k
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        if (size==0||num.length==0||size>num.length) return new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i< size;i++){
            set.add(num[i]);
        }
        int length = num.length;

        for (int i = size, j = 0;i<length;i++,j++){
            list.add(set.last());
            set.remove(num[j]);
            set.add(num[i]);
        }
        list.add(set.last());
        return list;
    }

    /**
     * 暴力解法
     */
    public ArrayList<Integer> maxInWindows1(int [] num, int size)
    {
        ArrayList<Integer> list=new ArrayList<Integer>();
        int max=0;
        if(num.length == 0 || size > num.length || size==0){
            return list;
        }
        for(int i=0;i <= num.length - size;i++){
            max=num[i];
            for(int j=i;j<size + i;j++){
                if(max < num[j]){
                    max=num[j];
                }
            }
            list.add(max);
        }
        return list;
    }

}
