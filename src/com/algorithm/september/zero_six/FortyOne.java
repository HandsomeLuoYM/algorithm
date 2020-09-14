package com.algorithm.september.zero_six;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/9/6 - 11:45
 * @describe
 */
public class FortyOne {

    /**
     * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
     * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
     * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,
     * 你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
     */

    /**
     * 自己的解法
     *
     * 思路：求n个数和为sum的中间值，n分为奇数和偶数，偶数时sum对i取余时余数为i的一半（因为除完时必须是.5才能找到他左右两个数），
     *       奇数时即除完必须整除
     *       同时还得判断他们不能小于0
     *
     * 运行时间：13ms
     *
     * 占用内存：9640k
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        int half = (sum+1)/2;
        for (int i=half;i>=2;i--){
            if (i%2==1&&sum%i==0&&(sum+1)/i>=i/2){
                ArrayList<Integer> list = new ArrayList<>(i);
                for (int a = -i/2;a<=i/2;a++)
                    list.add(sum/i+a);
                lists.add(list);
            }else if(i%2==0&&sum%i==i/2&&(sum+1)/i>=i/2){
                ArrayList<Integer> list = new ArrayList<>(i);
                for (int a = -i/2+1;a<=i/2;a++){
                    list.add(sum/i+a);
                }
                lists.add(list);
            }
        }
        return lists;
    }

    /**
     * 滑动窗口：自己实现
     *
     * 思路：设置一个左和右边界，当当前值小于sum时，证明还不够，需要移动右边界，当当前值大于sum时，证明已经超出，需要移动左边界，同时都得更改左右边界
     *       当值刚好时，左右边界就是他的两个限
     *
     * 运行时间：13ms
     *
     * 占用内存：9636k
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence1(int sum) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        if (sum==1) return lists;
        int r=1,l=1,all=1,half = (sum+1)/2;
        while (r<=half){
            if (all<sum){
                r++;
                all+=r;
            }else if (all>sum){
                all-=l;
                l++;
            }else {
                ArrayList<Integer> list= new ArrayList<>();
                for (int i=l;i<=r;i++){
                    list.add(i);
                }
                lists.add(list);
                r++;
                all+=r;
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        FortyOne fortyOne = new FortyOne();
        ArrayList<ArrayList<Integer>> lists = fortyOne.FindContinuousSequence1(15);
        lists.forEach(list ->{
            System.out.println(list);
        });

    }
}
