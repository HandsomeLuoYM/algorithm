package com.base.november.zero_serven;

import java.lang.reflect.Constructor;

/**
 * @author Ming
 * @date 2020/11/7 - 15:45
 * @describe
 */
public class LazyDouble {
    private volatile static LazyDouble LAZY;

    private LazyDouble(){}
    //双重检测锁模式 懒汉式单例 DCL懒汉式
    /**
     * 1、分配内存空间
     * 2、执行构造方法
     * 3、把这个对象指向这个空间
     *
     * ------> 指令重排会导致问题
     */
    private static LazyDouble getInstance(){
        if (LAZY == null){
            synchronized (LazyDouble.class){
                if (LAZY == null){
                    LAZY = new LazyDouble();
                }
            }
        }
        return LAZY;
    }

    public static void main(String[] args) throws Exception {
        LazyDouble instance = LazyDouble.getInstance();

        Constructor<LazyDouble> declaredConstructor = LazyDouble.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyDouble instance1 = declaredConstructor.newInstance();
        LazyDouble instance2 = declaredConstructor.newInstance();

        LazyDouble instance3 = LazyDouble.getInstance();

        System.out.println(instance == instance3);

    }
}
