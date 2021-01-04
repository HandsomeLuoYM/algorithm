package com.algorithm.two_zero_two_zero.august.two_two;

import java.util.ArrayList;

/**
 * @author Ming
 * @date 2020/8/22 - 14:42
 * @describe  679
 */
public class LeetCode {

    /**
     * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
     */

    /**
     * 暴力破解
     * 执行用时：1 ms, 在所有 Java 提交中击败了95.51%的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了95.39%的用户
     */
    public boolean judgePoint241(int[] nums) {
        double a=nums[0],b=nums[1],c=nums[2],d=nums[3];
        return judgePoint24_4(a,b,c,d);
    }
    boolean judgePoint24_1(double a){
        return a-24>-1e-6 && a-24<1e-6;
    }
    boolean judgePoint24_2(double a,double b){
        return (judgePoint24_1(a+b)||
                judgePoint24_1(a*b)||
                judgePoint24_1(a-b)||
                judgePoint24_1(b-a)||
                judgePoint24_1(a/b)||
                judgePoint24_1(b/a));
    }
    boolean judgePoint24_3(double a,double b,double c){
        return (judgePoint24_2((b+c),a)||
                judgePoint24_2((b*c),a)||
                judgePoint24_2((b-c),a)||
                judgePoint24_2((c-b),a)||
                judgePoint24_2((b/c),a)||
                judgePoint24_2((c/b),a)||
                judgePoint24_2((a+c),b)||
                judgePoint24_2((a*c),b)||
                judgePoint24_2((a-c),b)||
                judgePoint24_2((c-a),b)||
                judgePoint24_2((a/c),b)||
                judgePoint24_2((c/a),b)||
                judgePoint24_2((a+b),c)||
                judgePoint24_2((a*b),c)||
                judgePoint24_2((a-b),c)||
                judgePoint24_2((b-a),c)||
                judgePoint24_2((a/b),c)||
                judgePoint24_2((b/a),c));
    }
    boolean judgePoint24_4(double a,double b,double c,double d){
        return
                (judgePoint24_3((c+d),a,b)||
                        judgePoint24_3((c*d),a,b)||
                        judgePoint24_3((c-d),a,b)||
                        judgePoint24_3((d-c),a,b)||
                        judgePoint24_3((c/d),a,b)||
                        judgePoint24_3((d/c),a,b)||
                        judgePoint24_3((b+d),a,c)||
                        judgePoint24_3((b*d),a,c)||
                        judgePoint24_3((b-d),a,c)||
                        judgePoint24_3((d-b),a,c)||
                        judgePoint24_3((b/d),a,c)||
                        judgePoint24_3((d/b),a,c)||
                        judgePoint24_3((b+c),a,d)||
                        judgePoint24_3((b*c),a,d)||
                        judgePoint24_3((b-c),a,d)||
                        judgePoint24_3((c-b),a,d)||
                        judgePoint24_3((b/c),a,d)||
                        judgePoint24_3((c/b),a,d)||
                        judgePoint24_3((a+d),b,c)||
                        judgePoint24_3((a*d),b,c)||
                        judgePoint24_3((a-d),b,c)||
                        judgePoint24_3((d-a),b,c)||
                        judgePoint24_3((a/d),b,c)||
                        judgePoint24_3((d/a),b,c)||
                        judgePoint24_3((a+c),b,d)||
                        judgePoint24_3((a*c),b,d)||
                        judgePoint24_3((a-c),b,d)||
                        judgePoint24_3((c-a),b,d)||
                        judgePoint24_3((a/c),b,d)||
                        judgePoint24_3((c/a),b,d)||
                        judgePoint24_3((a+b),c,d)||
                        judgePoint24_3((a*b),c,d)||
                        judgePoint24_3((a-b),c,d)||
                        judgePoint24_3((b-a),c,d)||
                        judgePoint24_3((a/b),c,d)||
                        judgePoint24_3((b/a),c,d));
    }


    public boolean judgePoint24(int[] nums) {
        ArrayList<Double> A = new ArrayList<>();
        for (int v : nums) A.add((double) v);
        return solve(A);
    }

    //////////////////////////////////  官方题解  回溯法   ////////////////////////////////////
    private boolean solve(ArrayList<Double> nums) {
        // 没有可计算的，证明无解
        if (nums.size() == 0) return false;
        // le-6是浮点计算的精度误差，这里判断误差小于1e-6，这样就是正确结果
        if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 1e-6;


        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                //相同数不能取同一个
                if (i != j) {
                    // 找到当前排列下的其他两个元素
                    ArrayList<Double> nums2 = new ArrayList<>();
                    for (int k = 0; k < nums.size(); k++) {
                        //第三四个数
                        if (k != i && k != j) {
                            nums2.add(nums.get(k));
                        }
                    }

                    // k的四种计算情况
                    for (int k = 0; k < 4; k++) {
                        // 性能优化，当k<2时，对于+ or *不考虑顺序，
                        // 所以k<2 时，j > i与i > j的结果相同，所以有部分不用考虑
                        if (k < 2 && j > i) continue;
                        if (k == 0) nums2.add(nums.get(i) + nums.get(j));
                        if (k == 1) nums2.add(nums.get(i) * nums.get(j));
                        if (k == 2) nums2.add(nums.get(i) - nums.get(j));
                        // 避免除数为0
                        if (k == 3) {
                            if (nums.get(j) != 0) {
                                nums2.add(nums.get(i) / nums.get(j));
                            } else {
                                continue;
                            }
                        }
                        // 在这次计算后，判断剩下的元素是否符合要求
                        // 每次缩小计算范围
                        if (solve(nums2)) return true;
                        // 移除最后一个计算结果，因为最后的记过不满足要求
                        // 就是一种回溯方法，将前面添加的结果删除
                        nums2.remove(nums2.size() - 1);
                    }
                }
            }
        }
        return false;
    }


}
