package com.algorithm.two_zero_two_zero.july.three_one;

/**
   @author Ming
   @date 2020/7/31 - 16:38
   @describe
  */
public class LeetCode {
    /**
       魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，
       编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。
       若有多个魔术索引，返回索引值最小的一个。
       执行用时：
       0 ms, 在所有 Java 提交中击败了100.00%的用户
       内存消耗：40.9 MB, 在所有 Java 提交中击败了21.21%的用户
      */
    public int findMagicIndex(int[] nums) {
        int length = nums.length;
        for (int i=0;i<length;i++){
            if (i==nums[i]) return i;
        }
        return -1;
    }

    /**
       此问题如果用暴力的方法来解决，我们只需要对原数组从前往后进行一次遍历，找到第一个可行的位置返回即可，这里不再赘述。而本方法会进行一定程度的剪枝，在一些情况下会达到较优的时间复杂度，在最差情况下仍会退化成线性的时间复杂度，这里我们分两种情况讨论。

       第一种情况是数组中只有一个满足条件的答案。我们假设这个答案为 ii，那么意味着 [0 \ldots i-1][0…i−1] 的值均小于自身的下标，[i+1 \ldots n-1][i+1…n−1] 的值均大于自身的下标。我们将整个数组每个元素减去其自身所在的下标，那么最后的答案即为 00 所在的下标，且在 00 之前的元素均为负数，00 之后的元素均为正数。以 [-1,0,2,4,5][−1,0,2,4,5] 为例，减去自身下标以后以后得到 [-1,-1,0,1,1][−1,−1,0,1,1]，整个数组以 00 为分界点，前半部分均为负数，后半部分均为负数，因此我们可以使用二分查找在 O(\log n)O(logn) 的时间内找到答案 00 所在的下标，具体做法就是碰到负数舍弃左半边，碰到正数舍弃右半边即可。

       第二种情况是数组中存在多个满足条件的答案，此时我们发现整个数组不具有任何性质。以 [0,0,2,2,5][0,0,2,2,5] 为例，我们仍进行一次将每个元素减去其自身下标的操作，得到 [0,-1,0,-1,1][0,−1,0,−1,1]。目标是要找到第一个出现的 00，而由于数组中出现 00 的位置不确定，因此无法使用二分查找，但是我们可以依据此来进行一定程度的剪枝，我们剪枝的策略为：

       每次我们选择数组的中间元素，如果当前中间元素是满足条件的答案，那么这个位置往后的元素我们都不再考虑，只要寻找左半部分是否有满足条件的答案即可。

       否则我们需要查看左半部分是否有满足条件的答案，如果没有的话我们仍然需要在右半边寻找，使用的策略同上。

       我们可以依靠此策略定义一个递归函数：getAnswer(nums, left, right) 返回数组 \textit{nums}nums 的下标范围 [\textit{left},\textit{right}][left,right] 中第一个满足条件的答案，如果没有返回 -1−1。每次选择中间的位置 \textit{mid}mid，此时直接先递归调用数组左半部分 getAnswer(nums, left, mid - 1) 得到返回值 \textit{leftAnswer}leftAnswer，如果存在则直接返回，如果不存在则比较 \textit{nums}[\textit{mid}]nums[mid] 和 \textit{mid}mid 是否相等，如果相等则返回 \textit{mid}mid，否则需要递归调用 getAnswer(nums, mid + 1, right)

      */
    public int getAnswer(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (right - left) / 2 + left;
        int leftAnswer = getAnswer(nums, left, mid - 1);
        if (leftAnswer != -1) {
            return leftAnswer;
        } else if (nums[mid] == mid) {
            return mid;
        }
        return getAnswer(nums, mid + 1, right);
    }

    public int findMagicIndex2(int[] nums) {
        return getAnswer(nums, 0, (int) nums.length - 1);
    }

}