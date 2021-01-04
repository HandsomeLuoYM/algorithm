package com.algorithm.two_zero_two_zero.august.three_zero;

import java.util.*;

/**
 * @author Ming
 * @date 2020/8/30 - 22:20
 * @describe 27
 */
public class TwentySeven {

    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,则按字典序打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     */

    /**
     * 运行时间：85ms
     *
     * 占用内存：13092k
     */
    ArrayList list = new ArrayList<String>();
    Set set = new TreeSet<String>();
    public ArrayList<String> Permutation(String str) {
        if (str.equals("")||null==str) return list;
        int length = str.length();

        List<Character> chars = new ArrayList<>();
        for (int i=0;i<length;i++){
            chars.add(str.charAt(i));
        }

        getStringList(length,chars,"");
        list.addAll(set);
        return list;
    }
    private void getStringList(int length, List<Character> chars,String nowString){
        if (length==0){
            set.add(new String(nowString));
            return;
        }
        for (int i=0;i<length ;i++){
            Character nowCharacter = chars.get(i);
            nowString = nowString + nowCharacter;
            chars.remove(i);
            getStringList(length-1,chars,nowString);
            chars.add(i,nowCharacter);
            nowString = nowString.substring(0,nowString.length()-1);
        }
    }


    public static void main(String[] args) {
        TwentySeven twentySeven = new TwentySeven();
        ArrayList<String> permutation = twentySeven.Permutation("abcd");
        permutation.forEach(System.out::println);
        System.out.println(permutation.size());
    }
}
