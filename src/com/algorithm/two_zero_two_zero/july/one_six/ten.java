package com.algorithm.two_zero_two_zero.july.one_six;

/**
   @author Ming
   @date 2020/7/16 - 11:18
   @describe
  */
public class ten {

    /**
       我们可以用2 1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2 1的小矩形无重叠地覆盖一个2 n的大矩形，总共有多少种方法？
     **/


    /**
       运行时间：386ms
       <p>
       占用内存：9480k

       @param target
       @return
      */
    private int RectCover(int target) {
        if (target <= 0) return 0;
        if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return RectCover(target - 1) + RectCover(target - 2);
        }
    }


    /**
       运行时间：11ms
       <p>
       占用内存：9372k
       <p>
       如果到这里，还没有发现规律怎么办呢？
       那我们就再分析以下，从n=3到n=4，怎么来的呢？
       这里有2种情况：
       直接在n=3的情况下，再后面中添加一个竖着的。这个很显然成立，有3种情况
       然后横着的显然能添加到n-2的情况上，也就是在n=2后面，添加2个横着的。有2种情况
       通过以上分析，发现刚好和图中的个数一样。
       所以总结：f [n]表示2 n大矩阵 的方法数。
       可以得出：f[n] = f[n-1] + f[n-2]，初始条件f[1] = 1, f[2] =2
       所以代码可用递归，记忆递归，和动态规划和递推
       这里只写递推代码：
      */
    public int RectCover1(int target) {

        if (target == 0 || target == 1 || target == 2) return target;
        int a = 1, b = 2, c = 0;
        for (int i = 3; i <= target; ++i) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
