package com.examination.网易;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Ming
 * @date 2021/3/27 - 16:16
 * @describe
 */
public class Main4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int result = Integer.parseInt(sc.nextLine());
        int zekou = Integer.parseInt(sc.nextLine());
        String[] s = sc.nextLine().split(" ");
        int[] values = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            values[i] = Integer.parseInt(s[i]);
        }
        Arrays.sort(values);
        dfs(values, 0, 0, result);
        System.out.println(min - zekou);
    }
    static int min = Integer.MAX_VALUE;
    private static void dfs(int[] values, int index, int now, int result) {
        if (index >= values.length) return;
        if (now >= result) {
            if (min > now) min = now;
            return;
        } else {
            dfs(values, index + 1, now, result);
            dfs(values, index + 1, now + values[index], result);
        }
    }


}
