package com.algorithm.august.one_three;

/**
   @author Ming
   @date 2020/8/13 - 11:17
   @describe
  */
public class LeetCode {

    /**
       43. 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

       num1 和 num2 的长度小于110。
       num1 和 num2 只包含数字 0-9。
       num1 和 num2 均不以零开头，除非是数字 0 本身。
       不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

       输入: num1 = "2", num2 = "3"
       输出: "6"

       输入: num1 = "123", num2 = "456"
       输出: "56088"

      */

    /**
       自己的暴力破解法

       执行用时：25 ms, 在所有 Java 提交中击败了19.99%的用户
       内存消耗：40.1 MB, 在所有 Java 提交中击败了31.91%的用户
      */
    public String add(String num1, String num2) {
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
        ans.reverse();
        return ans.toString();
    }

    public String multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")) return "0";
        int length1 = num1.length(),length2 = num2.length();
        String result = "";
        for (int i = 0;i<length2;i++){

            StringBuilder part=new StringBuilder();//部分乘积
            int num = 0;//进位
            int ch = num2.charAt(length2-i-1) - '0';//乘数

            for (int j = 0;j<length1;j++){
                int a = ch *(num1.charAt(length1-j-1)-'0') + num;
                num = a/10;
                part.append(a%10);
            }
            if(num!=0) part.append(num);
            part.reverse();
            for (int k = 0; k < i ; k++){
                part.append("0");
            }

            result = this.add(result, part.toString());
        }

        return result;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
       令m和n分别表示 num1和numg的长度，并且它们均不为0，则num1和numg的乘积的长度为m＋n-1或m＋ n。简单证明如下∶

            ·如果 num1和 num2都取最小值，则num1=10^(m-1)，num2=10^(n-1)，num1×num2=10^(m+n-2)，乘积的长度为 m＋ n-1;

            ·如果 num1和 num2都取最大值，则 num1=10^(m)-1，num2=10^(n)-1，num×num2=10^(m+n)−10^m−10^n+1，乘积显然小于10^(m+n)且大于10^(m+n-1)，
            因此乘积的长度为 m＋ n。

       由于num1 和num2的乘积的最大长度为 m＋n，因此创建长度为m＋n的数组ansArr用于存储乘积。
       对于任意0≤i<m和0≤j<n，num【】×num2【】的结果位于ansArmi+j＋1】，如果ansArr【i+j＋1】210，则将进位部分加到 ansAr7i+j】。

       最后，将数组 ansArr转成字符串，如果最高位是0则舍弃最高位。
      */
    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        //累加
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        //校准，防止int数组中有大于10的数
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        //转换为字符串
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        int[] ansArr = new int[2];
        ansArr[0] += 12;
        System.out.println(ansArr);
    }

}
