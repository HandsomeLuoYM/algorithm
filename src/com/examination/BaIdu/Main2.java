package com.examination.BaIdu;

import java.util.*;

/**
 * @author Ming
 * @date 2021/3/21 - 19:40
 * @describe
 */
public class Main2 {
    public static class Main {

        static Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        public static int calc(int[] numbers, int index) {
            if (index == numbers.length) return 0;
            List<Integer> list = map.get(numbers[index]);
            int rs = Integer.MAX_VALUE;
            for (int i = 0; i < list.size(); i++) {
                int j = list.get(i);
                if (j <= index) continue;
                rs = Math.min(rs, calc(numbers, j + 1) + 1);
            }
            rs = Math.min(rs, calc(numbers, index + 1) + 1);
            return rs;
        }
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            int[] numbers = new int[Integer.parseInt(s)];
            s = sc.nextLine();
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = s.charAt(i) - '0';
            }
            for(int i = 0; i < numbers.length; i++) {
                List<Integer> list = map.getOrDefault(numbers[i], new ArrayList<>());
                list.add(i);
                map.put(numbers[i], list);
            }
            System.out.println(calc(numbers, 0));
        }
    }
}
