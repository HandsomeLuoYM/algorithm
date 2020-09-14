package com.algorithm.interview;

/**
 * @author Ming
 * @date 2020/9/5 - 11:50
 * @describe
 */
public class Shopee {

    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        boolean flag = false;
        int len = param.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if ((c >= 48 && c<=59) || (c>=65 && c<=90) || (c>=97&&c<=122)) {
                if (!flag) {
                    if ((c >= 48 && c<=59)){
                        sb.append(c);
                    }else if (c>=65 && c<=90){
                        sb.append(Character.toLowerCase(c));
                    }else {
                        sb.append(Character.toUpperCase(c));
                    }
                    flag = true;
                }else {
                    sb.append(c);
                }
            } else {
                flag=false;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = Shopee.underlineToCamel("//a//b\\b");
        System.out.println("132".compareTo("123"));
    }

}
