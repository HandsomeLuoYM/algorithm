package com.examination.腾讯;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Ming
 * @date 2021/3/24 - 19:51
 * @describe
 */
public class TxTest02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = Integer.parseInt(in.nextLine());
        for (int i = 0; i < number; i++) {
            //获取数据
            String[] dataNumbers = in.nextLine().split(" ");
            int dataNumber = Integer.parseInt(dataNumbers[0]), n = Integer.parseInt(dataNumbers[1]);
            String[] data = in.nextLine().split(" ");
            int[] intData = new int[dataNumber];
            //转换成int数据
            for (int j = 0; j < dataNumber; j++) {
                intData[j] = Integer.parseInt(data[j]);
            }
            Arrays.sort(intData);
            if (n * 2 > dataNumber) {
                System.out.println("数据错误！");
                continue;
            }
            int rs = 0;
            for (int start = 0; start < n; start++) {
                rs += intData[start] * intData[2 * n - start - 1];
            }
            System.out.println(rs);
        }
    }
}
