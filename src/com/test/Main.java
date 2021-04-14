package com.test;

import java.net.Socket;
import java.util.*;

/**
 * @author Ming
 * @date 2021/3/13 - 16:00
 * @describe
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = 0;
        String line;
        String[] split;
        int[][] list;
        line = scanner.nextLine();
        String[] num = line.split(" ");
        list = new int[Integer.parseInt(num[0])][Integer.parseInt(num[1])];
        while (row < Integer.parseInt(num[0])) {
            line = scanner.nextLine();
            split = line.split(" ");
            for (int i = 0; i < split.length; i++) {
                list[row][i] = Integer.parseInt(split[i]);
            }
            row++;
        }
        int[][] ints = test01(list);
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[0].length; j++) {
                System.out.println(ints[i][j]);
            }
        }
    }
    private static int[][] test01(int[][] number){
        int row = number.length, cell = number[0].length;
        int[][] rs = new int[cell][row];
        for (int i = 0; i < cell; i++) {
            for (int j = 0; j < row; j++) {
                rs[i][j] = number[j][i];
            }
        }
        return rs;
    }
}

class Main1 {
    public static void main(String[] args) {
        //先拿数据
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        int[] number = new int[Integer.parseInt(s[0])];
        int row = Integer.parseInt(s[0]);
        String[] n = scanner.nextLine().split(" ");
        for (int i = 0; i < n.length; i++) {
            number[i] = Integer.parseInt(n[i]);
        }
        int now = Integer.parseInt(s[1]);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < number.length && i < now; i++) {
            map.put(number[i], map.getOrDefault(number[i], 0) + 1);
        }
        //输出并且判断
        int max = number[0];
        for (int i = now; i < number.length; i++) {
            max = number[i - 1];

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= map.get(max)) {
                    max = entry.getValue() > map.get(max) ? entry.getValue() : (entry.getKey() < max ? entry.getKey() : max);
                }
            }
            System.out.println(max);

            map.put(number[i], map.getOrDefault(number[i], 0) + 1);

            if (map.get(number[i - now ]) - 1 == 0) {
                map.remove(number[i - now]);
            }else {
                map.put(number[i - now], map.get(number[i - now]) - 1);
            }
        }
        max = number[number.length - 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= map.get(max)) {
                max = entry.getValue() > map.get(max) ? entry.getValue() : (entry.getKey() < max ? entry.getKey() : max);
            }
        }
        System.out.println(max);
        scanner.close();
    }
}
class a{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char[] array = s.toCharArray();
        int index =0;
        List<Long> rs = new ArrayList<>();
        while(index < array.length) {
            if(array[index] >= '0' && array[index] <= '9') {
                StringBuilder tep = new StringBuilder();
                while (index < array.length && array[index] >= '0' && array[index] <= '9'){
                    tep.append(array[index]);
                    index++;
                }
                rs.add(Long.parseLong(tep.toString()));
            }else {
                index++;
            }
        }
        rs.stream().sorted().forEach(System.out::println);

    }
}

class a3{
    public static void main(String[] args) {
        Socket socket = new Socket();
    }
}