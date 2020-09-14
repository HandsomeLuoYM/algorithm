package com.algorithm.august.zero_three;


public class LeetCode {

    /**
       给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

       注意：

       num1 和num2 的长度都小于 5100.
       num1 和num2 都只包含数字 0-9.
       num1 和num2 都不包含任何前导零。
       你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。

      */
    /**
       执行用时：3 ms, 在所有 Java 提交中击败了79.86%的用户
       内存消耗：39.8 MB, 在所有 Java 提交中击败了52.43%的用户
      */
    private int calculation(char char1,char char2,StringBuilder result,int flag){
        int  a = char1-'0';
        int  b = char2-'0';
        int c = a+b+flag;
        result.append(c%10);
        return c/10;
    }

    public String addStrings(String num1, String num2) {
        Integer num1Length = num1.length();
        Integer num2Length = num2.length();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();

        StringBuilder result = new StringBuilder();
        int flag = 0;
        for (int i=0;i<num1Length||i<num2Length;i++){
            char ca1 = i<num1Length ? chars1[num1Length-i-1]:'0';
            char ca2 = i<num2Length ? chars2[num2Length-i-1]:'0';
            flag = calculation(ca1,ca2,result,flag);
        }
        if (flag==1) result.append(1);
        result.reverse();
        return result.toString();
    }


    /**
       官方题解
      */
    public String addStrings2(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }


    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        String s = leetCode.addStrings("1", "9");
        System.out.println(s);
    }
}
