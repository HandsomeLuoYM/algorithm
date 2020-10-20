package com.test.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Ming
 * @date 2020/10/14 - 20:27
 * @describe
 */
public class Test5 {
    static AtomicStampedReference<Integer> a = new AtomicStampedReference(new Integer(1),1);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("初始值："+a.getReference());
        new Thread(new Runnable() {
            @Override
            public void run() {
                int expectReference = a.getReference();
                int newReference = expectReference + 1;
                int expectStamp = a.getStamp();
                int newStamp = expectStamp + 1;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean isSuccess = a.compareAndSet(expectReference, newReference, expectStamp, newStamp);
                System.out.println("主线程执行结果：" + isSuccess + "，值为：" + a.getReference());
            }
        },"主线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a.compareAndSet(a.getReference(),a.getReference()+1,a.getStamp(),a.getStamp()+1);
                System.out.println("干扰线程执行结果："  + a.getReference());
                a.compareAndSet(a.getReference(),a.getReference()-1,a.getStamp(),a.getStamp()+1);
                System.out.println("干扰线程执行结果："  + a.getReference());

            }
        },"干扰线程").start();
    }
}
