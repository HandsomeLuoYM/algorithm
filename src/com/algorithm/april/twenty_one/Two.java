package com.algorithm.april.twenty_one;

/**
   @author Ming
   @date 2020/4/21 - 10:09
   @describe 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
   例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
  */
public class Two {

    /**
       自己暴力破解
       <p>
       运行时间：24ms
       <p>
       占用内存：9520k
      */
    public String replaceSpace(StringBuffer str) {
        return str.toString().replace(" ", "%20");
    }

    /**
       网上方法
       <p>
       运行时间：16ms
       <p>
       占用内存：9600k
      */
    public String replaceSpaceOther1(StringBuffer str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
       数组形式破解
       <p>
       运行时间：24ms
       <p>
       占用内存：9572k
      */
    public String replaceSpaceOther2(StringBuffer str) {
        int spacenum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                spacenum++;
            }
        }
        int oldLength = str.length();
        int oldIndex = oldLength - 1;
        int newLength = oldLength + spacenum * 2;
        str.setLength(newLength);
        int newIndex = newLength - 1;
        for (; oldIndex >= 0 && oldLength < newLength; oldIndex--) {
            if (str.charAt(oldIndex) == ' ') {
                str.setCharAt(newIndex--, '0');
                str.setCharAt(newIndex--, '2');
                str.setCharAt(newIndex--, '%');
            } else {
                str.setCharAt(newIndex--, str.charAt(oldIndex));
            }
        }
        return str.toString();
    }

}
