package com.examination.网易;

import java.util.Scanner;

/**
 * @author Ming
 * @date 2021/3/27 - 15:34
 * @describe
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = Integer.parseInt(sc.nextLine());
        int[] path = new int[length];
        String[] data = sc.nextLine().split(" ");
        for (int i = 0; i < data.length; i++) {
            path[i] = Integer.parseInt(data[i]);
        }
        int left = 0, rs = 0, flag = 0, index = 1;
        while (index < length && path[index] == path[index - 1]) index++;
        if (path[index] > path[index - 1]) {
            flag = 1;
        }else {
            flag = -1;
        }
        for (; index < length; index++) {
            if (path[index] == path[index - 1]) {
                flag = 0;
                left = index;
                continue;
            }else if (path[index] > path[index - 1]) {
                if (flag == 0) flag = 1;
                else if (flag == 1) flag = 2;
                else if (flag == 2) {
                    left = index - 2;
                } else if (flag < 0) {
                    if (flag == -1) {
                        left = index - 1;
                        flag = 1;
                    }else {
                        flag = 1;
                    }
                }
                rs = Math.max(rs, index - left);
            }else {
                if (flag == 0) flag = -1;
                else if (flag == -1) flag = -2;
                else if (flag == -2) {
                    left = index - 2;
                }else if (flag > 0) {
                    if (flag == 1) {
                        left = index - 1;
                        flag = -1;
                    }else {
                        flag = -1;
                    }
                }
                rs = Math.max(rs, index - left);
            }
        }
        System.out.println(rs + 1);
    }
}
