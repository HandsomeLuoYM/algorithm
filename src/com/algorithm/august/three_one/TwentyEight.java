package com.algorithm.august.three_one;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author Ming
 * @date 2020/8/31 - 11:55
 * @describe
 */
public class TwentyEight {

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
     * 超过数组长度的一半，因此输出2。如果不存在则输出0。
     */

    /**
     * 运行时间：10ms
     *
     * 占用内存：9440k
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        int length = array.length/2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int arr : array){
            if (map.containsKey(arr)){
                map.put(arr,map.get(arr)+1);
            }else {
                map.put(arr,1);
            }
        }
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            if (entry.getValue()>length) return entry.getKey();
        }
        return 0;
    }


}
