package com.examination.阿里.第二题;


import java.util.Map;
import java.util.Scanner;

/**
 * @author Ming
 * @date 2021/3/22 - 19:32
 * @describe
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] number = new int[n];
        String[] s = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(s[i]);
        }
        System.out.println(dfs(number, 0, number.length - 1));
    }

    private static int dfs(int[] number, int start, int end) {
        if (start > end) return 0;
        if (start == end) return number[start];
        int l = start, r = end, rAll = 0, lAll = 0;
        while (l < r) {
            if (rAll > lAll) {
                lAll += number[l];
                l++;
            }else {
                rAll += number[r];
                r--;
            }
        }
        int min = l;
        if (rAll > lAll) {
            if (lAll + number[min] >= rAll) {
                return Math.max(rAll + dfs(number, min + 1, end), lAll + dfs(number, start, min - 1));
            }else {
                return lAll + number[min] + dfs(number, start, min);
            }
        }else {
            if (rAll + number[min] >= lAll) {
                return Math.max(rAll + dfs(number, min + 1, end), lAll + dfs(number, start, min - 1));
            }else {
                return rAll + number[min] + dfs(number, min, end);
            }
        }
    }
}
