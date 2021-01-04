package com.algorithm.two_zero_two_zero.september.zero_four;

/**
 * @author Ming
 * @date 2020/9/4 - 11:29
 * @describe
 */
public class ThirtyFive {

    /**
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。
     * 即输出P%1000000007
     *
     * 题目保证输入的数组中没有的相同的数字
     *
     * 数据范围：
     * 	对于%50的数据,size<=10^4
     * 	对于%75的数据,size<=10^5
     * 	对于%100的数据,size<=2*10^5
     */
    /**
     * 自己的暴力解法：case通过率为50.00%
     */
    public int InversePairs(int [] array) {
        int length = array.length;
        int all=0;
        for (int i=0;i<length;i++){
            for (int j = i;j<length;j++){
                if (array[i]>array[j]) all++;
            }
        }
        return all%1000000007;
    }

    /**
     * 运行时间：340ms
     *
     * 占用内存：56372k
     *
     * 在归并排序的过程中 后一个数组的数如小于前一个数组的数，则一定能够构成逆序对且逆序对的数目可计算，
     * 因为待归并的两个数组提前已经归并排序过，所以不会出现像前面那样少统计或者多统计的情况出现。
     * 思路：[A，B]中的逆序对=[A]的逆序对+[B]中的逆序对+将A，B混排在一起的逆序对
     * 而将A，B混排在一起的逆序对求解看下面：
     */
    public class Solution {
        private int cnt;
        private void MergeSort(int[] array, int start, int end){
            if(start>=end)return;
            int mid = (start+end)/2;
            MergeSort(array, start, mid);
            MergeSort(array, mid+1, end);
            MergeOne(array, start, mid, end);
        }
        private void MergeOne(int[] array, int start, int mid, int end){
            int[] temp = new int[end-start+1];
            int k=0,i=start,j=mid+1;
            while(i<=mid && j<= end){
                //如果前面的元素小于后面的不能构成逆序对
                if(array[i] <= array[j]) {
                    temp[k++] = array[i++];
                } else{
                    //如果前面的元素大于后面的，那么在前面元素之后的元素都能和后面的元素构成逆序对
                    temp[k++] = array[j++];
                    cnt = (cnt + (mid-i+1))%1000000007;
                }
            }
            while(i<= mid) {
                temp[k++] = array[i++];
            }
            while(j<=end) {
                temp[k++] = array[j++];
            }
            for(int l=0; l<k; l++){
                array[start+l] = temp[l];
            }
        }
        public int InversePairs(int [] array) {
            MergeSort(array, 0, array.length-1);
            return cnt;
        }
    }
}
