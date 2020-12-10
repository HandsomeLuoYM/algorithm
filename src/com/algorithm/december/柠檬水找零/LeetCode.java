package com.algorithm.december.柠檬水找零;

/**
 * @author Ming
 * @date 2020/12/10 - 9:29
 * @describe
 */
public class LeetCode {

    /**
     * 自己的做法（模拟 + 贪心）：
     *      统计当前五块和十块的数量。然后进行相对应的减
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.72%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了58.53%的用户
     */
    public boolean lemonadeChange(int[] bills) {
        int length = bills.length;
        int number[] = new int[2];
        for (int i = 0; i<length; i++){
            if (bills[i]==5){
                number[0]++;
            }else if (bills[i]==10){
                if (number[0]>0) number[0]--;
                else return false;
                number[1]++;
            }else {
                if (number[1]>0){
                    if (number[0]>0){
                        number[1]--;
                        number[0]--;
                    }else return false;
                }else if (number[0]>=3){
                    number[0] = number[0]-3;
                }else return false;
            }
        }
        return true;
    }

}
