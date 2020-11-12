package com.base.november.one_zero;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Ming
 * @date 2020/11/10 - 21:46
 * @describe
 */
public class Test01 {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("45534653434343");
                Thread.sleep(2000);
                return 1008611;
            }
        });
        //阻塞的
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

}
