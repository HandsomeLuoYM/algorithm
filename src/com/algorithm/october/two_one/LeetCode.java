package com.algorithm.october.two_one;

/**
 * @author Ming
 * @date 2020/10/21 - 16:29
 * @describe
 */
public class LeetCode {

    /**
     * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
     *
     * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
     *
     * 输入：name = "alex", typed = "aaleex"
     * 输出：true
     * 解释：'alex' 中的 'a' 和 'e' 被长按。
     *
     * 输入：name = "saeed", typed = "ssaaedd"
     * 输出：false
     * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
     *
     * 输入：name = "leelee", typed = "lleeelee"
     * 输出：true
     *
     * 输入：name = "laiden", typed = "laiden"
     * 输出：true
     * 解释：长按名字中的字符并不是必要的。
     */

    /**
     * 自己的思路：
     *      利用双指针来表示两个字符串走到的位置，同时要标记name前一个字符的值，当出现不同时可能是长按情况，当遍历完时判断一下后续即可。
     * 执行用时：1 ms, 在所有 Java 提交中击败了86.83%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了74.48%的用户
     */
    public boolean isLongPressedName(String name, String typed) {
        int nIndex = 1, tIndex = 1, nLength = name.length(), tLength = typed.length();
        if (name.charAt(0) != typed.charAt(0)) return false;
        char before = name.charAt(0);
        while (nIndex<nLength && tIndex<tLength){
            if (name.charAt(nIndex) == typed.charAt(tIndex)){
                before = name.charAt(nIndex);
                nIndex++;
                tIndex++;
            }else {
                if (typed.charAt(tIndex) == before){
                    tIndex++;
                    continue;
                }
                return false;
            }
        }
        if (nIndex < nLength) return false;
        else {
            while (tIndex < tLength){
                if (typed.charAt(tIndex) != before)
                    return false;
                tIndex++;
            }
            return true;
        }
    }
}
