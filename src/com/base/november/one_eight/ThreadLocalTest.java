package com.base.november.one_eight;

/**
 * @author Ming
 * @date 2020/11/18 - 17:47
 * @describe
 */
public class ThreadLocalTest {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String content;

    private String getContent() {
        return threadLocal.get();
    }

    private void setContent(String content) {
        threadLocal.set(content);
    }

    public static void main(String[] args) {
        ThreadLocalTest demo = new ThreadLocalTest();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setContent(Thread.currentThread().getName() + "的数据");
                    System.out.println(Thread.currentThread().getName() + "--->" + demo.getContent());
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }
}
