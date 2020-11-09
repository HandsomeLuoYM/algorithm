package com.base.november.zero_serven;

/**
 * @author Ming
 * @date 2020/11/8 - 21:33
 * @describe
 */
public class DCLSingle {

    private volatile static DCLSingle DCL_SINGLE;

    private DCLSingle(){}
    //双重检测锁模式 懒汉式单例 DCL懒汉式
    /**
     * 1、分配内存空间
     * 2、执行构造方法
     * 3、把这个对象指向这个空间
     *
     * ------> 指令重排会导致问题
     */
    private static DCLSingle getInstance(){
        if (DCL_SINGLE == null){
            synchronized (DCLSingle.class){
                if (DCL_SINGLE == null){
                    DCL_SINGLE = new DCLSingle();
                }
            }
        }
        return DCL_SINGLE;
    }
}
