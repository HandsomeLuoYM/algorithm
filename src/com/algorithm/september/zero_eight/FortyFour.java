package com.algorithm.september.zero_eight;

/**
 * @author Ming
 * @date 2020/9/8 - 14:48
 * @describe 翻转单词顺序
 */
public class FortyFour {

    /**
     * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，
     * 写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，
     * 但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
     * 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     */
    /**
     * 思路：先把字符串拆分成数组，然后从右往左拼接（先排除空字符串）
     * 运行时间：12ms
     *
     * 占用内存：9516k
     */
    public String ReverseSentence(String str) {
        if (str.trim().equals("")) return str;
        String[] split = str.split("\\s");
        StringBuilder stringBuilder = new StringBuilder();
        for (int length = split.length-1;length>=0;length--){
            stringBuilder.append(split[length]).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    /**
     * 第一种方法：转换成string的字符串数组，然后拼接。这个拼接需要注意空格的拼接（最后一个单词结尾是没有空格分隔的）
     *
     * 第二种方法：剑指offer的思想，先翻转所有的字符，然后利用滑动窗口的思想，遇到' '就翻转，然后两者一起跳转到' '后重新滑动。
     */
    public String ReverseSentence1(String str) {
        if (str == null || str.trim().length() == 0) return str;
        char[] chars = str.toCharArray();
        reverseChars(chars, 0, str.length() - 1);
        // 利用滑动窗口
        // 遇到' '执行翻转
        int l = 0;
        int r = 0;
        while (l < str.length()) {
            if (chars[r] == ' ') {
                reverseChars(chars, l, r - 1);
                // 交换完之后,一起跳过' '
                r++;
                l = r;
            }
            if (r == str.length() - 1) {
                reverseChars(chars, l, r);
                // 到了最后交换玩就break，否则r会出现越界，可以在while中加对r的判断
                break;
            }
            r++;
        }
        return String.valueOf(chars);
    }
    private void reverseChars(char[] chars, int l, int r) {
        while (l < r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
    }
}
