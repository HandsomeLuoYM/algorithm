package com.algorithm.september.zero_serven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming
 * @date 2020/9/7 - 13:25
 * @describe
 */
public class FortyTwo {
    /**
     * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
     * 对应每个测试案例，输出两个数，小的先输出。
     */

    /**
     * 思路：设置hash，当hashmap中存在另一个数时，可以设置两个值，如果不存在则将这个数添加到hashmap中
     * 运行时间：10ms
     *
     * 占用内存：9440k
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        int length = array.length,a1=sum,a2=sum;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i=0;i<length;i++){
            if (map.containsKey(sum-array[i])){
                if (array[i]*(sum-array[i])<a1*a2){
                    a1=array[i]>(sum-array[i])?(sum-array[i]):array[i];
                    a2=array[i]<(sum-array[i])?(sum-array[i]):array[i];
                }
            }else {
                map.put(array[i],array[i]);
            }
        }
        System.out.println(a1*a2);
        ArrayList<Integer> list = new ArrayList<>();
        if (a1+a2!=sum||length<2) return list;
        list.add(a1);
        list.add(a2);
        return list;
    }

    /**
     * 思路：因为数组是递增的，所以设置左右指针来确定是否有两数相加为sum，越靠左或右得出的两个数乘积越小
     * 运行时间：10ms
     *
     * 占用内存：9568k
     */
    public ArrayList<Integer> FindNumbersWithSum1(int [] array, int sum) {
        if (array.length==0) return new ArrayList<>();
        int length = array.length,l=0,r=array.length-1,now=array[l]+array[r];
        while (l!=r){
            if (now>sum){
                now-=array[r];
                r--;
                now+=array[r];
            }else if (now<sum){
                now-=array[l];
                l++;
                now+=array[l];
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(array[l]);
                list.add(array[r]);
                return list;
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {

        FortyTwo fortyTwo = new FortyTwo();
        ArrayList<Integer> list = fortyTwo.FindNumbersWithSum1(new int[]{1, 2, 4, 7, 11, 15}, 15);
        System.out.println(list.toString());

    }
}
