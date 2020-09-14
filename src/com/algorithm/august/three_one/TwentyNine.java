package com.algorithm.august.three_one;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Ming
 * @date 2020/8/31 - 16:33
 * @describe
 */
public class TwentyNine {
    /**
     *  输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
     */

    /**
     * 运行时间：14ms
     *
     * 占用内存：9652k
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (k>input.length) return new ArrayList<>();
        int length = input.length;
        for (int i=0;i<k;i++){
            int sign = i;
            for (int j =i;j<length;j++){
                if (input[sign]>input[j]){
                    sign=j;
                }
            }
            int temp=input[sign];
            input[sign]=input[i];
            input[i]=temp;
        }
        ArrayList list = new ArrayList<Integer>();
        for (int i=0;i<k;i++){
            list.add(input[i]);
        }
        return list;
    }



}
