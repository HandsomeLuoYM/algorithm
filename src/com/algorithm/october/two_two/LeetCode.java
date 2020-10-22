package com.algorithm.october.two_two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ming
 * @date 2020/10/22 - 13:19
 * @describe
 */
public class LeetCode {

    /**
     * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
     *
     * 输入：S = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8]
     * 解释：
     * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
     * 每个字母最多出现在一个片段中。
     * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
     *
     */

    /**
     * 自己的思路：
     *      记录每个点的开始位置，然后把每个单位都拆成一个一个的，最后再合并
     *      合并规则：
     *          当第一次出现时，记录位置
     *          不是第一次出现时，合并之前位置到现在位置，标记为同一个区间
     *       最后只需要判断记录中区间的情况即可
     * 执行用时：3 ms, 在所有 Java 提交中击败了96.93%的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了91.91%的用户
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> list = new ArrayList<>();
        int length = S.length();
        int[] sign = new int[26];
        int[] sSign = new int[S.length()];
        //初始化
        for (int i = 0; i < length; i++){
            sSign[i] = i;
        }
        Arrays.fill(sign,-1);

        for (int i = 0; i<length; i++){
            if (sign[S.charAt(i)-97]==-1){
                //记录第一个出现点
                sign[S.charAt(i)-97] = i;
            }else {
                //合并区间
                int number = sSign[sign[S.charAt(i)-97]];
                for (int j = sign[S.charAt(i)-97]; j<=i; j++){
                    sSign[j] = number;
                }
            }
        }
        int before = sSign[0];
        int number = 0;
        //检查个数
        for (int i = 0; i<length; i++){
            if (before==sSign[i])
                number++;
            else {
                list.add(new Integer(number));
                before = sSign[i];
                number = 1;
            }
        }
        list.add(number);
        return list;
    }

    /**
     * 方法一：贪心算法 + 双指针
     * 思路与算法
     *
     * 由于同一个字母只能出现在同一个片段，显然同一个字母的第一次出现的下标位置和最后一次出现的下标位置必须出现在同一个片段。
     * 因此需要遍历字符串，得到每个字母最后一次出现的下标位置。
     *
     * 在得到每个字母最后一次出现的下标位置之后，可以使用贪心算法和双指针的方法将字符串划分为尽可能多的片段，具体做法如下。
     *
     * 从左到右遍历字符串，遍历的同时维护当前片段的开始下标start 和结束下标 end，初始时 start=end=0。
     *
     * 对于每个访问到的字母 cc，得到当前字母的最后一次出现的下标位置 cend，则当前片段的结束下标一定不会小于endc，因此令end=max(end,endc)。
     *
     * 当访问到下标 end 时，当前片段访问结束，当前片段的下标范围是[start,end]，长度为 1end−start+1，将当前片段的长度添加到返回值，
     * 然后令 start=end+1，继续寻找下一个片段。
     *
     * 重复上述过程，直到遍历完字符串。
     *
     */
    class Solution {
        public List<Integer> partitionLabels(String S) {
            int[] last = new int[26];
            int length = S.length();
            for (int i = 0; i < length; i++) {
                last[S.charAt(i) - 'a'] = i;
            }
            List<Integer> partition = new ArrayList<Integer>();
            int start = 0, end = 0;
            for (int i = 0; i < length; i++) {
                end = Math.max(end, last[S.charAt(i) - 'a']);
                if (i == end) {
                    partition.add(end - start + 1);
                    start = end + 1;
                }
            }
            return partition;
        }
    }

}
