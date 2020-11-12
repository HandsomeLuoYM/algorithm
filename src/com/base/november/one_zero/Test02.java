package com.base.november.one_zero;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Ming
 * @date 2020/11/10 - 21:47
 * @describe
 */
public class Test02 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        System.out.println("time:" + System.currentTimeMillis());
        scheduledThreadPool.schedule(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                System.out.println("time:" + System.currentTimeMillis());
                return null;
            }
        }, 2, TimeUnit.SECONDS);
        scheduledThreadPool.shutdown();
    }
}
