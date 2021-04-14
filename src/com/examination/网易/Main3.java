package com.examination.网易;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Ming
 * @date 2021/3/27 - 15:52
 * @describe
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = Integer.parseInt(sc.nextLine());
        long[] values = new long[length];
        String[] data = sc.nextLine().split(" ");
        for (int i = 0; i < data.length; i++) {
            values[i] = Long.parseLong(data[i]);
        }

        List<Long> list = new LinkedList<Long>();
        for (int i = 0; i < length; i++) {
            int size = list.size();
            for (int j = 0; j < size; j++) {
                list.add(list.get(j) + values[i]);
            }
            list.add(values[i]);
        }
        long rs = -1;
        for (Long aLong : list) {
            if (aLong % 6 == 0) rs = Math.max(rs, aLong);
        }
        System.out.println(rs);
    }
}
