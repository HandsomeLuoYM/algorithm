package com.base.november.zero_serven;

/**
 * @author Ming
 * @date 2020/11/7 - 15:45
 * @describe
 */
public class Lazy {
    private static Lazy LAZY;

    private Lazy(){}
    //适合单线程
    private static Lazy getInstance(){
        if (LAZY == null){
            LAZY = new Lazy();
        }
        return LAZY;
    }
}
