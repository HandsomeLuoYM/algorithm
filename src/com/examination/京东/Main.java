package com.examination.京东;

import java.util.Scanner;

/**
 * @author Ming
 * @date 2021/3/27 - 19:41
 * @describe
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int length = s.length(), rs = 0;
        for (int i = 0; i < length; ) {
            if (s.charAt(i) == 'H') {
                i++;
                int start = i, number = 0;
                while (i < length && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    number = number * 10 + s.charAt(i) - '0';
                    i++;
                }
                if (number != 0) rs += number;
                else rs++;
            } else if (s.charAt(i) == 'C') {
                i++;
                int start = i, number = 0;
                while (i < length && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    number = number * 10 + s.charAt(i) - '0';
                    i++;
                }
                if (number != 0) rs = rs + number * 12;
                else rs = rs + 12;
            } else if (s.charAt(i) == 'O') {
                i++;
                int start = i, number = 0;
                while (i < length && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    number = number * 10 + s.charAt(i) - '0';
                    i++;
                }
                if (number != 0) rs = rs + number * 16;
                else rs = rs + 16;
            } else {
                i++;
                int start = i, number = 0;
                while (i < length && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    number = number * 10 + s.charAt(i) - '0';
                    i++;
                }
                if (number != 0) rs = rs + number * 14;
                else rs = rs + 14;
            }
        }
        System.out.println(rs);
    }
}
