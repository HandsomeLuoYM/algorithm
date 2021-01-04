package com.algorithm.two_zero_two_zero.may.one_three;

/**
   @author Ming
   @date 2020/5/14 - 1:57
   @describe

   把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
   输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
   例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
   NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
  */
public class Six {

    /**
       运行时间：373ms

       占用内存：28692k
       @param array
       @return
       自己的写法
      */
    public int minNumberInRotateArray(int [] array) {
        if (array.length==0) return 0;
        int i =0;
        for(;i<array.length-1;i++){
            if (array[i]>array[i+1]){
                System.out.println(array[i+1]);
                break;
            }
        }
        return array[i+1];
    }

    /**
       运行时间：243ms

       占用内存：28300k
       @param array
       @return

       分析：二分查找变种，没有具体的值用来比较。那么用中间值和高低位进行比较，看处于递增还是递减序列，进行操作缩小范围。

       处于递增：low上移

       处于递减：high下移（如果是high-1，则可能会错过最小值，因为找的就是最小值）

       其余情况：low++缩小范围
      */
    int minNumberInRotateArray2(int [] array) {
        if(array.length==0)
            return 0;

        int low = 0;
        int high = array.length - 1;
        int mid = 0;

        while(low < high){
            // 子数组是非递减的数组，10111
            if (array[low] < array[high])
                return array[low];
            mid = low + (high - low) / 2;
            if(array[mid] > array[low])
                low = mid + 1;
            else if(array[mid] < array[high])
                high = mid;
            else low++;
        }
        return array[low];
    }
}
