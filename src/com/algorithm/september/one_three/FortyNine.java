package com.algorithm.september.one_three;

/**
 * @author Ming
 * @date 2020/9/10 - 13:57
 * @describe
 */
public class FortyNine {
    /**
     *
     * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
     *
     * +2147483647
     * 1a33
     *
     * 2147483647
     * 0
     */

    /**
     * 自己的做法，挨个判断，但是第一个字符需要特殊处理，它允许为 +（45）或 - （43）
     *
     * 运行时间：9ms
     *
     * 占用内存：9236k
     */
    public int StrToInt(String str) {
        int num = 0,length = str.length();
        if (str.length()<=0||(str.charAt(0)<48&&str.charAt(0)!=43&&str.charAt(0)!=45)||str.charAt(0)>57) return 0;
        char ch ;
        int i=0,sh=1;
        if (str.charAt(0)==43||str.charAt(0)==45){
            if (str.charAt(0)==45) sh=-1;
            i++;
        }
        for (;i<length;i++){
            ch = str.charAt(i);
            if (ch<48||ch>57) return 0;
            num = num*10+ch-48;
        }
        return num*sh;
    }

    /**
     * 又是捕获异常
     * @param str
     * @return
     */
    public int StrToInt1(String str) {
        Integer res=0;
        try {
            res = new Integer(str);
        } catch (NumberFormatException e) {

        } finally {
            return res;
        }
    }

    /**
     * 正则表达式，从小到大，速率慢
     */
    public int StrToInt2(String str) {
        // \d代表[0-9] 但是要写成\\d才行。
        if(!str.matches("[+,-]?\\d+")) return 0;
        int len = str.length();
        int i = len-1;
        long res = 0;  //long类型，避免溢出。不能用int

        while(i>=0&&str.charAt(i)>='0'&&str.charAt(i)<='9'){
            res += Math.pow(10,len-1-i)*(str.charAt(i)-'0');
            i--;
        }
        res = (str.charAt(0) == '-' ? -res : res);
        //溢出就返回0，用long类型的res来比较，
        //如果定义为int res,那再比较就没有意义了，int范围为[-2147483648,2147483647]
        if(res>Integer.MAX_VALUE|| res<Integer.MIN_VALUE)return 0;
        return (int)res;
    }

}
