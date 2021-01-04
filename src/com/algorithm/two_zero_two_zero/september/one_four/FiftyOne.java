package com.algorithm.two_zero_two_zero.september.one_four;

/**
 * @author Ming
 * @date 2020/9/14 - 17:10
 * @describe
 */
public class FiftyOne {

    /**
     * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
     * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
     * （注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
     * 对于A长度为1的情况，B无意义，故而无法构建，因此该情况不会存在。
     */

    /**
     * 自己的解法：返回当前节点后续的所有操作，然后由前面节点传递前面参数所有值的乘积
     * 运行时间：11ms
     *
     * 占用内存：9380k
     */
    public int[] multiply(int[] A) {
        int length = A.length,all = 1;
        int[] B = new int[length];
        int dfs = dfs(A, B, A[0], 1);
        B[0] = dfs;
        return B;
    }

    private int dfs(int[] A,int[] B,int all,int now){
        if (A.length==now) return 1;
        int after = dfs(A,B,all*A[now],now+1);
        B[now] = all*after;
        return  after*A[now];
    }

    /**
     * 官方题解
     * 分为两次赋值，左和右，由两次遍历来解决
     */
    public int[] multiply1(int[] A){
        int[] B = new int[A.length];
        for (int i=1; i<A.length; ++i) {
            B[i] = B[i-1] * A[i-1]; // left[i]用B[i]代替
        }
        int tmp = 1;
        for (int j=A.length-2; j>=0; --j) {
            tmp *= A[j+1]; // right[i]用tmp代替
            B[j] *= tmp;
        }
        return B;
    }

}
