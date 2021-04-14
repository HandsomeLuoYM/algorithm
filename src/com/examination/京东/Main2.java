package com.examination.京东;

import java.util.Scanner;

/**
 * @author Ming
 * @date 2021/3/27 - 20:01
 * @describe
 */
public class Main2 {
    static int[] x = new int[]{0, 1, 0, -1};
    static int[] y = new int[]{1, 0, -1, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] number = sc.nextLine().split(" ");
        int n = Integer.parseInt(number[0]), m = Integer.parseInt(number[1]);
        int[][] k = new int[n][m];
        String s = sc.nextLine();
        String[] s1;
        for (int i = 0; i < n; i++) {
            s1 = s.split(" ");
            for (int j = 0; j < m; j++) {
                k[i][j] = Integer.parseInt(s1[j]);
            }
        }

    }
}
