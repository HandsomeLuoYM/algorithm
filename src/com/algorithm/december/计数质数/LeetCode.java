package com.algorithm.december.计数质数;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ming
 * @date 2020/12/3 - 9:46
 * @describe 204. 计数质数
 */
public class LeetCode {

    /**
     * 统计所有小于非负整数 n 的质数的数量。
     *
     * 输入：n = 10
     * 输出：4
     * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     *
     * 输入：n = 0
     * 输出：0
     *
     * 输入：n = 1
     * 输出：0
     */
    /**
     * 思路：
     *      暴力挨个遍历，调试需要去除是偶数的数
     * 执行用时：692 ms, 在所有 Java 提交中击败了7.97%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了96.45%的用户
     */
    public int countPrimes(int n) {
        if (n<=2) return 0;
        int all = 1;
        for (int i = 3; i<n; i=i+2){
            if (isPrimes(i)) all++;
        }
        return all;
    }
    private boolean isPrimes(int n){
        int sqrt = (int)Math.sqrt(n);
        for (int i = 2; i<=sqrt; i++){
            if (n%i==0) return false;
        }
        return true;
    }

    /**
     * 埃氏筛
     *
     * 枚举没有考虑到数与数的关联性，因此难以再继续优化时间复杂度。接下来我们介绍一个常见的算法，该算法由希腊数学家厄拉多塞（Eratosthenes）提出，
     * 称为厄拉多塞筛法，简称埃氏筛。
     *
     * 我们考虑这样一个事实：如果 x 是质数，那么大于 x 的 x 的倍数 2x,3x,… 一定不是质数，因此我们可以从这里入手。
     *
     * 我们设 isPrime[i] 表示数 i 是不是质数，如果是质数则为 1，否则为 00。从小到大遍历每个数，
     * 如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），即 00，这样在运行结束的时候我们即能知道质数的个数。
     *
     * 这种方法的正确性是比较显然的：这种方法显然不会将质数标记成合数；另一方面，当从小到大遍历到数 x 时，
     * 倘若它是合数，则它一定是某个小于 x 的质数 y 的整数倍，故根据此方法的步骤，我们在遍历到 y 时，
     * 就一定会在此时将 xx 标记为 isPrime[x]=0。因此，这种方法也不会将合数标记为质数。
     *
     * 当然这里还可以继续优化，对于一个质数 x，如果按上文说的我们从 2x 开始标记其实是冗余的，
     * 应该直接从 x⋅x 开始标记，因为 2x,3x,… 这些数一定在 x 之前就被其他数的倍数标记过了，
     * 例如 2 的所有倍数，3 的所有倍数等。
     *
     */
    class Solution {
        public int countPrimes(int n) {
            int[] isPrime = new int[n];
            Arrays.fill(isPrime, 1);
            int ans = 0;
            for (int i = 2; i < n; ++i) {
                if (isPrime[i] == 1) {
                    ans += 1;
                    if ((long) i * i < n) {
                        for (int j = i * i; j < n; j += i) {
                            isPrime[j] = 0;
                        }
                    }
                }
            }
            return ans;
        }
    }

    /**
     * 线性筛
     */
    class Solution1 {
        public int countPrimes(int n) {
            List<Integer> primes = new ArrayList<Integer>();
            int[] isPrime = new int[n];
            Arrays.fill(isPrime, 1);
            for (int i = 2; i < n; ++i) {
                if (isPrime[i] == 1) {
                    primes.add(i);
                }
                for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                    isPrime[i * primes.get(j)] = 0;
                    if (i % primes.get(j) == 0) {
                        break;
                    }
                }
            }
            return primes.size();
        }
    }

}
