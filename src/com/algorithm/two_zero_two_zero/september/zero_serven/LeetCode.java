package com.algorithm.two_zero_two_zero.september.zero_serven;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming
 * @date 2020/9/7 - 13:07
 * @describe 801. 使序列递增的最小交换次数
 */
public class LeetCode {

    /**
     * 我们有两个长度相等且不为空的整型数组 A 和 B 。
     *
     * 我们可以交换 A[i] 和 B[i] 的元素。注意这两个元素在各自的序列中应该处于相同的位置。
     *
     * 在交换过一些元素之后，数组 A 和 B 都应该是严格递增的（数组严格递增的条件仅为A[0] < A[1] < A[2] < ... < A[A.length - 1]）。
     *
     * 给定数组 A 和 B ，请返回使得两个数组均保持严格递增状态的最小交换次数。假设给定的输入总是有效的。
     *
     * 示例:
     * 输入: A = [1,3,5,4], B = [1,2,3,7]
     * 输出: 1
     * 解释:
     * 交换 A[3] 和 B[3] 后，两个数组如下:
     * A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
     * 两个数组均为严格递增的。
     *
     * 注意:
     *
     * A, B 两个数组的长度总是相等的，且长度的范围为 [1, 1000]。
     * A[i], B[i] 均为 [0, 2000]区间内的整数。
     * 通过次数3,754提交次数9,253
     *
     */

    /**
     * 暴力破解超时了
     */
    public int minSwap(int[] A, int[] B) {
        return getShortCnt(A,B,1,0);
    }

    public int getShortCnt(int[] A, int[] B,int now,int cnt){
        if (now==A.length) return cnt;
        int y=Integer.MAX_VALUE,n=Integer.MAX_VALUE;
        if (A[now]>A[now-1]&&B[now]>B[now-1]){
            n = getShortCnt(A, B,now+1,cnt);
        }
        if (A[now]>B[now-1]&&B[now]>A[now-1]){
            int temp = A[now];
            A[now]=B[now];
            B[now]=temp;
            y=getShortCnt(A, B,now+1,cnt+1);
            temp = A[now];
            A[now]=B[now];
            B[now]=temp;
        }

        return y < n ?y:n;
    }

    /**
     * 思路：记忆法，A99
     */
    Map<String,Integer> map = new HashMap<>();
    public int minSwap1(int[] A, int[] B) {
        return getShortCnt1(A, B, 1, 0);
    }
    public int getShortCnt1(int[] A, int[] B,int now,int cnt){
        if (now==A.length) return cnt;
        int y=Integer.MAX_VALUE,n=Integer.MAX_VALUE;
        if (A[now]>A[now-1]&&B[now]>B[now-1]){
            if (map.containsKey(A[now] + "" + B[now])) return map.get(A[now] + "" + B[now])+cnt;
            n = getShortCnt1(A, B,now+1,cnt);
        }
        if (A[now]>B[now-1]&&B[now]>A[now-1]){
            if (map.containsKey(B[now] + "" + A[now])) return map.get(B[now] + "" + A[now])+cnt;
            int temp = A[now];
            A[now]=B[now];
            B[now]=temp;
            y=getShortCnt1(A, B,now+1,cnt+1);
            temp = A[now];
            A[now]=B[now];
            B[now]=temp;
        }
        int result = y < n ?y:n;
        if (n==result) map.put(A[now] + "" + B[now],n-cnt);
        else map.put(B[now] + "" + A[now],y-cnt);
        return result;
    }

    public int minSwap2(int[] A, int[] B) {
        return this.minSwap2(A,B,0,0);
    }

    public int minSwap2(int[] A,int[] B,int i,int result){
        if(i>=A.length){
            return result;
        }
        if(i>=1&&(A[i]<=A[i-1]||B[i]<=B[i-1])){//必换
            int t=A[i];
            A[i]=B[i];
            B[i]=t;
            if(A[i]<=A[i-1]||B[i]<B[i-1]){
                B[i]=A[i];
                A[i]=t;
                return Integer.MAX_VALUE;
            }
            int result1 = this.minSwap2(A,B,i+1,result+1);
            B[i]=A[i];
            A[i]=t;
            return result1;
        }else{//可换
            int result1 = this.minSwap2(A,B,i+1,result);//不换
            int result2 = Integer.MAX_VALUE;
            if(i>=1){
                int t=A[i];
                A[i]=B[i];
                B[i]=t;
                if(A[i]>A[i-1]&&B[i]>B[i-1]){
                    result2 = this.minSwap2(A,B,i+1,result+1);//换
                }
                B[i]=A[i];
                A[i]=t;
            }
            return Math.min(result1,result2);
        }
    }

    public int minSwap3(int[] A, int[] B) {
        int no = 0;
        int yes= 1;
        int n=-1;
        int y=-1;
        for(int i=1;i<A.length;i++){
            n = 1001;
            y = 1001;
            //这次不换上次换 或 这次换上次不换
            if(A[i]>B[i-1]&&B[i]>A[i-1]){
                n=Math.min(yes,n);
                y=Math.min(no+1,y);
            }
            //这次不换上次不换 或 这次换上次换
            if(A[i]>A[i-1]&&B[i]>B[i-1]){
                n=Math.min(no,n);
                y=Math.min(yes+1,y);
            }
            no=n;
            yes=y;
        }
        return Math.min(no,yes);
    }

}
