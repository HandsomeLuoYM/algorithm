package com.examination.阿里.第一题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Ming
 * @date 2021/3/22 - 18:57
 * @describe
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] firstLine, secondLine;
        int[] persons;
        List<String> rs = new LinkedList<String>();
        while (sc.hasNextInt()) {
            firstLine = sc.nextLine().split(" ");
            secondLine = sc.nextLine().split(" ");
            int number =Integer.parseInt(firstLine[0]), weight = Integer.parseInt(firstLine[1]);
            persons = new int[number];
            for (int i = 0; i < number; i++) {
                persons[i] = Integer.parseInt(secondLine[i]);
            }
            if (judge(persons, weight, 0, 0)) {
                rs.add("YES");
            }else {
                rs.add("NO");
            }
        }
        for (String r : rs) {
            System.out.println(r);
        }
    }

    private static boolean judge(int[] persons, int weight, int index, int rs) {
        if (index == persons.length) {
            return rs == weight;
        }
        if (rs == weight){
            return true;
        } else if (rs > weight){
            return false;
        }else {
            return judge(persons, weight, index + 1, rs + persons[index]) || judge(persons, weight, index + 1, rs);
        }

    }
}
