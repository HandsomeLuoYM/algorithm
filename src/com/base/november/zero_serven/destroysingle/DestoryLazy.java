package com.base.november.zero_serven.destroysingle;

import com.base.november.zero_serven.LazyDouble;

import java.lang.reflect.Constructor;

/**
 * @author Ming
 * @date 2020/11/7 - 16:52
 * @describe
 */
public class DestoryLazy {
    private volatile static DestoryLazy LAZY;


    /**
     //这种会出现如果对象还没有创建，那么就可以使用反射来创建对象多个
     //检测锁模式 懒汉式单例 DCL懒汉式
    private DestoryLazy(){
        synchronized (DestoryLazy.class){
            if (LAZY != null){
                throw new RuntimeException("不要试图使用反射来创建对象");
            }
        }
    }
     */
    /**
     * 红绿灯的做法，设置一个信号量来标志，无法多次获取，但是也有问题
     * 如果还没new那么 LAZY 会无法 new 了
     */
    private static boolean FLAG = false;
    private DestoryLazy(){
        synchronized (DestoryLazy.class){
            if (FLAG == false){
                FLAG = true;
            }else {
                throw new RuntimeException("不要试图使用反射来创建对象");
            }
        }
    }

    /**
     * 1、分配内存空间
     * 2、执行构造方法
     * 3、把这个对象指向这个空间
     *
     * ------> 指令重排会导致问题
     */
    private static DestoryLazy getInstance(){
        if (LAZY == null){
            synchronized (LazyDouble.class){
                if (LAZY == null){
                    LAZY = new DestoryLazy();
                }
            }
        }
        return LAZY;
    }

    public static void main(String[] args) throws Exception {
//        DestoryLazy instance = DestoryLazy.getInstance();

        Constructor<DestoryLazy> declaredConstructor = DestoryLazy.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        DestoryLazy instance1 = declaredConstructor.newInstance();
//        DestoryLazy instance2 = declaredConstructor.newInstance();

        DestoryLazy instance = DestoryLazy.getInstance();


//        System.out.println(instance == instance2);

    }
}