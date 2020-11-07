package com.base.november.zero_two;

import java.util.concurrent.*;

/**
 * @author MingL
 * @date 2020/11/2 - 17:09
 * @describe
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory()
        );
        for (int i = 0; i<9; i++){
            pool.execute(new Task(i));
        }
      pool.shutdownNow();


    }
}
