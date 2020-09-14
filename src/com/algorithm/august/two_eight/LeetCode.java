package com.algorithm.august.two_eight;

/**
 * @author Ming
 * @date 2020/8/28 - 9:48
 * @describe
 */
public class LeetCode {

    /**
     * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
     *
     * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
     *
     * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。
     *
     *
     * 输入: "UD"
     * 输出: true
     * 解释：机器人向上移动一次，然后向下移动一次。所有动作都具有相同的幅度，因此它最终回到它开始的原点。因此，我们返回 true。
     *
     * 输入: "LL"
     * 输出: false
     * 解释：机器人向左移动两次。它最终位于原点的左侧，距原点有两次 “移动” 的距离。我们返回 false，因为它在移动结束时没有返回原点。
     *
     */

    /**
     * 自己题解
     * 执行用时：6 ms, 在所有 Java 提交中击败了60.03%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了49.82%的用户
     */
    public boolean judgeCircle(String moves) {

        int[] nums = {0,0};
        int length = moves.length();

        for (int i=0;i<length;i++){
            switch (moves.charAt(i)){
                case 'U': nums[0]++;break;
                case 'D': nums[0]--;break;
                case 'L': nums[1]++;break;
                case 'R': nums[1]--;break;
                default: break;
            }
        }
        return nums[0]==0&&nums[1]==0;
    }


}
