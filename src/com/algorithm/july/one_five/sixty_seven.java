package com.algorithm.july.one_five;

/**
   @author Ming
 * @date 2020/7/15 - 1:43
 * @describe
 */
public class sixty_seven {

    /**
     * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），
     * 每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     */


    /**
     * 运行时间：11ms
     *
     * 占用内存：9828k
     * @param target
     * @return
     */
    public int cutRope(int target) {
        int i = target/3;
        double pow = Math.pow(3, i - 1);
        if (target%3==0){
            return ((int)pow)*3;
        }else if (target%3==1){
            return ((int)pow)*4;
        }else {
            return ((int)pow)*2*3;
        }

    }
}
