package com.test.test;

/**
 * @author Ming
 * @date 2020/10/12 - 19:29
 * @describe
 */
public class Test2 {

    void waitForSignal() throws InterruptedException {
        Object obj = new Object();
        synchronized (Thread.currentThread()){
            obj.notify();
            ((Thread)obj).wait();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test2 test2 = new Test2();
        test2.waitForSignal();
    }
}
