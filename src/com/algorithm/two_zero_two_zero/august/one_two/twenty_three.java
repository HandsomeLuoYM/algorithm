package com.algorithm.two_zero_two_zero.august.one_two;

/**
   @author Ming
   @date 2020/8/10 - 17:06
   @describe
  */
public class twenty_three {

    /**

       运行时间：10ms

       占用内存：9404k

       输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
       如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
      */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length==0) return true;
        return judge(0,sequence.length-1,sequence);
    }

    private boolean judge(int start,int end ,int[] sequence){
        int length = end - start;
        int i=start;
        if (length<=2) return true;
        while (i<length && sequence[i]<sequence[end]){
            i++;
        }
        int m = i-1;
        while (i<length && sequence[i]>sequence[end]){
            i++;
        }
        System.out.println("m="+m+"======i="+i);
        if (i!=end) return false;
        else return judge(start,m,sequence) && judge(m+1,end-1,sequence);
    }


    public static void main(String[] args) {
        int[] a = {4,8,6,12,16,14,10};
        twenty_three tt = new twenty_three();
        boolean b = tt.VerifySquenceOfBST(a);
        System.out.println(b);
    }
}
