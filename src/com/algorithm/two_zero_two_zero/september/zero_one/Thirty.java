package com.algorithm.two_zero_two_zero.september.zero_one;

/**
 * @author Ming
 * @date 2020/9/1 - 15:01
 * @describe
 */
public class Thirty {
    /**
     * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
     * 今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,
     * 当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,
     * 并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
     * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
     */

    /**
     * 定义一个最大值以及一个当前值，再赋初值，在遍历的时候，判断当前值是否大于最大值，是则改变最大值。而当当前值小于0则放弃掉前面所有值，将当前值赋为0
     *
     * 运行时间：10ms
     *
     * 占用内存：9428k
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int max=array[0] , all=array[0] , length = array.length;
        for (int i=1 ;i<length;i++ ){
            all=all+array[i];
            if (all>max) max = all;
            if (all<0) all=0;
        }
        return max;
    }


}
