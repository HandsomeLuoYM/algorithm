package com.base.november.zero_serven;

/**
 * @author Ming
 * @date 2020/11/7 - 15:42
 * @describe
 */
public class Hungry {
    private static Hungry HUNGRY = new Hungry();

    private Hungry(){}
    //适合多线程
    public static Hungry getInstance(){
        return HUNGRY;
    }
}
