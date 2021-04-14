package com.examination.腾讯;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Ming
 * @date 2021/3/24 - 19:44
 * @describe
 */
public class TxTest {
    public static void main(String[] args) {
        int[] rs = calc(new int[]{2, 7, 11, 15}, 9);
        for (int r : rs) {
            System.out.println(r);
        }
    }

    public static int[] calc(int[] numbers, int target) {
        int length = numbers.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            if (set.contains(target - numbers[i])) {
                return new int[]{target - numbers[i], numbers[i]};
            }else {
                set.add(numbers[i]);
            }
        }
        return null;
    }


}
