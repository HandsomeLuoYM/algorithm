package com.base.november.zero_serven;

import java.lang.reflect.Constructor;

/**
 * @author Ming
 * @date 2020/11/7 - 15:45
 * @describe
 */
public class LazyDouble {
    private static LazyDouble LAZY;

    private LazyDouble(){}

    private synchronized static LazyDouble getInstance(){
        if (LAZY == null){
            LAZY = new LazyDouble();
        }
        return LAZY;
    }
}
