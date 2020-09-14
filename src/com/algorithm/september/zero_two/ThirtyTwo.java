package com.algorithm.september.zero_two;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Ming
 * @date 2020/9/2 - 16:51
 * @describe
 */
public class ThirtyTwo {

    /**
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
     * 打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
     * 则打印出这三个数字能排成的最小数字为321323。
     */

    /**
     * 自己的做法（先排序，再添加）
     * 运行时间：13ms
     *
     * 占用内存：9684k
     */
    public String PrintMinNumber(int [] numbers) {
        int length = numbers.length;
        for (int i=0;i<length;i++){
            int change=i;
            for (int j=i+1;j<length;j++){
                if (Integer.parseInt(numbers[change]+""+numbers[j])>Integer.parseInt(numbers[j]+""+numbers[change])) change = j;
            }
            if (i!=change){
                int temp = numbers[i];
                numbers[i] = numbers[change];
                numbers[change] = temp;
            }
        }
        StringBuilder all=new StringBuilder();
        for (int i = 0;i<length;i++){
            all.append(numbers[i]);
        }
        return all.toString();
    }

    public String PrintMinNumber1(int [] numbers) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for(int i : numbers){
            arrayList.add( i + "" );
        }

        Collections.sort(arrayList, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                int i = 0, j = 0;
                while(i < o1.length() || j < o2.length()){
                    if(j==o2.length()) j-=o2.length();
                    if(i==o1.length()) i-=o1.length();
                    if(o1.charAt(i) < o2.charAt(j)){
                        return -1;
                    }else if(o1.charAt(i) > o2.charAt(j)){
                        return 1;
                    }
                    i++; j++;
                }
                return 0;
            }
        });

        StringBuilder stringBuilder2 = new StringBuilder();
        for(String s : arrayList){
            stringBuilder2.append(s);
        }
        return stringBuilder2.toString();
    }

    public static void main(String[] args) {
        ThirtyTwo thirtyTwo = new ThirtyTwo();
        String s = thirtyTwo.PrintMinNumber1(new int[]{32, 322});
        System.out.println(s);
    }

}
