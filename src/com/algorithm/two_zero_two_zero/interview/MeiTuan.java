package com.algorithm.two_zero_two_zero.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Ming
 * @date 2020/8/15 - 16:10
 * @describe
 */
public class MeiTuan {

    public int reverseOrder(){
        Scanner scanner = new Scanner(System.in);
        String need = scanner.nextLine();
        String[] s = need.split("\\s+");
        int length = Integer.parseInt(s[0]);
        int[][] car = new int[Integer.parseInt(s[0])][2];
        for (int i=0;i<length;i++){
            String[] s1 = scanner.nextLine().split("\\s+");
            car[i][0] = Integer.parseInt(s1[0]);
            car[i][1] = Integer.parseInt(s1[1]);
        }
        return getNum(car,Integer.parseInt(s[1]),Integer.parseInt(s[2]),0);
    }
    Map<String,Integer> memory = new HashMap<>();
    public int getNum(int[][] car ,int carNum1,int carNum2,int index){
        if (index>=car.length||carNum1+carNum2>car.length-index||(carNum1<=0&&carNum2<=0)) return 0;
        if (memory.containsKey(carNum1+"-"+carNum2+"-"+index)) return memory.get(carNum1+"-"+carNum2+"-"+index);
        int max;
        if (carNum1>0&&carNum2>0){
            int num1 = getNum(car, carNum1 - 1, carNum2, index + 1) + car[index][0];
            int num2 = getNum(car, carNum1, carNum2 - 1, index + 1) + car[index][1];
            int num3 = getNum(car, carNum1, carNum2, index + 1);
            max = Math.max(num1,Math.max(num2,num3));
        }else if(carNum1==0){
            max = Math.max(getNum(car, carNum1, carNum2, index + 1),
                    getNum(car, carNum1, carNum2 - 1, index + 1) + car[index][1]);
        }else if (carNum2==0){
            max = Math.max(getNum(car, carNum1, carNum2, index + 1),
                    getNum(car, carNum1+1, carNum2, index + 1) + car[index][0]);
        }else {
            max = getNum(car, carNum1, carNum2, index + 1);
        }
        memory.put(carNum1+"-"+carNum2+"-"+index,max);
        return max;
    }

    public static void main(String[] args) {
        MeiTuan meiTuan = new MeiTuan();
        int i = meiTuan.reverseOrder();
        System.out.println(i);
    }
}
