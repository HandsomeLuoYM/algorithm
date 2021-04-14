package com.examination.BaIdu;

import java.util.Scanner;

//package com.examination.BaIdu;
//
//import java.util.*;
//
//public class Main {
//    public static void main1(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String[] num = sc.nextLine().split(" ");
//        int n = Integer.parseInt(num[0]), m = Integer.parseInt(num[1]), a = 0;
//        int[] numbers = new int[n];
//        String[] numbersString = sc.nextLine().split(" ");
//        for (int i = 0; i < numbersString.length; i++) {
//            numbers[i] = Integer.parseInt(numbersString[i]);
//        }
//        String s;
//        List<String> list = new ArrayList<>();
//        while (a < m) {
//            s = sc.nextLine();
//            list.add(s);
//            a++;
//        }
//        for (int i = 0; i < list.size(); i++) {
//            String[] split = list.get(i).split(" ");
//            if (split[0].equals("1")){
//                //单
//                calc(true, Integer.parseInt(split[1]), Integer.parseInt(split[2]), numbers);
//            }else {
//                calc(false, Integer.parseInt(split[1]), Integer.parseInt(split[2]), numbers);
//            }
//        }
//    }
//
//    private static void calc(boolean flag, int l, int r, int[] numbers) {
//        int dang = 0, shuang = 0;
//        for (int i = l - 1; i < r; i++) {
//            if (numbers[i] % 2 == 0) {
//                shuang++;
//            }else {
//                dang++;
//            }
//        }
//        if (flag) {
//            System.out.println(calculate(dang));
//        }else {
//            int num1 = calculate(shuang), num2 = calculate(dang);
//
//            System.out.println(num1 + num1 * num2);
//        }
//    }
//
//    private static int calculate(int n) {
//        int partfm = 1, partfz = 1, rs = 0;
//        for (int i = 1; i <= n; i++) {
//            for (int j = 0; j < i; j++) {
//                partfm = partfm * (n - j);
//            }
//            for (int j = 1; j <= i; j++) {
//                partfz = partfz * j;
//            }
//            rs += partfm / partfz;
//            partfm = partfz = 1;
//        }
//        return rs;
//    }
//
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String[] num = sc.nextLine().split(" ");
//        int n = Integer.parseInt(num[0]), m = Integer.parseInt(num[1]), a = 0;
//        int[] numbers = new int[n];
//        String[] numbersString = sc.nextLine().split(" ");
//        for (int i = 0; i < numbersString.length; i++) {
//            numbers[i] = Integer.parseInt(numbersString[i]);
//        }
//        String s;
//        List<String> list = new ArrayList<>();
//        while (a < m) {
//            s = sc.nextLine();
//            list.add(s);
//            a++;
//        }
//
//        for (int i = 0; i < list.size(); i++) {
//            String[] split = list.get(i).split(" ");
//            if (split[0].equals("1")){
//                //单
//
//            }else {
//
//            }
//        }
//    }
//
//    private static int cal(boolean flag, int l, int r, int[] numbers){
//        List<Integer> dang = new LinkedList<Integer>(), shuang = new LinkedList<Integer>();
//        for (int i = l - 1; i < r; i++) {
//            if (flag) {
//                if (numbers[i]%2 == 1)
//                    dang.add(numbers[i]);
//            }else {
//                shuang.add(numbers[i]);
//            }
//        }
//        if (flag) {
//
//        }else {
//
//        }
//        return 0;
//    }
//
//    private static int dfs(List<Integer> list, boolean flag, int index, int now, List<Integer> nowList){
//        Set<List<Integer>> set = new HashSet<>();
//        if ()
//    }
//}
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        solution(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
    }


    static void solution(int n, int m){
        int mod = 1000000007;
        int[][][] dp = new int[n+1][m+1][m+1];
        for (int i = 1; i <= m; i++) {
            dp[i][i][0] = 1;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m && i > j; j++){
                for (int k = 1; k <= m; k++) {
                    if (j == k) continue;
                    for (int l = 0; l <= m; l++) {
                        if (j == l) continue;
                        dp[i][j][k] = (dp[i][j][k] + dp[i-j][k][l] % mod) % mod;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= m; j++) {
                res = (res + dp[n][i][j] % mod) % mod;
            }
        }
        System.out.println(res);
    }
}