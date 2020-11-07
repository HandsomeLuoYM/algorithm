package com.base.november.zero_two;

/**
 * @author Ming
 * @date 2020/11/2 - 17:35
 * @describe
 */
public class Task implements Runnable {

    private int nov;

    public Task(int nov) {
        this.nov = nov;
    }

    @Override
    public void run() {
        System.out.println("当前执行的线程.........."+Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务"+nov+"执行完毕！");
    }
}
