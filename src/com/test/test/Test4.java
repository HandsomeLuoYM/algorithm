package com.test.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ming
 * @date 2020/10/14 - 20:27
 * @describe
 */
public class Test4 {

    static volatile int NUMBER = 0;

    private static void add() throws InterruptedException {
        Thread.sleep(5);
        /**
         *  NUMBER++ 被拆分成三步
         *      1、从内存中获取 NUMBER 的值，赋给 A : A = NUMBER
         *      2、对 A 进行 +1 得到 B： B = A + 1
         *      3、把 B 的值写回 NUMBER：NUMBER = B
         *      升级第三步：
         *          1、获取锁
         *          2、获取 NUMBER 的最新值，记作LV
         *          3、判断 LV 是否等于 A，如果相等，则将 B 的值赋给NUMBER，并且返回true，如果不相等返回false，线程再自旋请求修改值
         */
       int expectNumber;
       for (;;)
           if (compareAndSwap((expectNumber=getNumber()),expectNumber+1))
                    break;
    }

    /**
     * 模拟底层 sun.misc.Unsafe 的 compareAndSwap 方法，所以这里我加了锁，底层并不是加锁，它底层是对数据总线加锁，synchronized是对字节码加锁
     * @param expectNumber 期望值
     * @param newNumber 要交换的值
     * @return 返回交换是否成功
     */
    public static synchronized boolean compareAndSwap(int expectNumber, int newNumber){
        if (expectNumber == getNumber()){
            NUMBER = newNumber;
            return true;
        }
        return false;
    }
    public static int getNumber(){return NUMBER;}

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j<100; j++){
                        try {
                            add();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("最后执行结果：" + NUMBER + "。执行时间为：" + (System.currentTimeMillis() - start) + "ms");
    }
}
