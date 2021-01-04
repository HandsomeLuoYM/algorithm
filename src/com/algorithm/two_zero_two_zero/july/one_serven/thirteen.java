package com.algorithm.two_zero_two_zero.july.one_serven;

/**
 * @author Ming
 * @date 2020/7/17 - 1:46
 * @describe
 */
public class thirteen {

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
     *
     * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     */


    /**
     * 运行时间：10ms
     *
     * 占用内存：9532k
     * 自己的解题思路
     *
     * @param array
     */
    public void reOrderArray(int [] array) {
        int length = array.length;
        for (int i=0;i<length;i++){
            if (array[i]%2==1){
                continue;
            }else {
                //找到奇数
                for (int j=i+1;j<length;j++){
                    if (array[j]%2==1){
                        int temp = array[j];
                        for (int k = j;k>i;k--){
                            array[k]=array[k-1];
                        }
                        array[i]=temp;
                        break;
                    }
                }
            }
        }
    }
}
