package com.base.november.one_eight;

/**
 * @author Ming
 * @date 2020/11/20 - 11:16
 * @describe
 */
public class SynchronizedTest {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        SynchronizedTest demo02 = new SynchronizedTest();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(){
                @Override
                public void run() {
                    synchronized (SynchronizedTest.class){
                        demo02.setContent(Thread.currentThread().getName() + "的数据");
                        String content = demo02.getContent();
                        System.out.println(Thread.currentThread().getName() + "--->" + content);
                    }
                }
            };
            t.setName("线程" + i);
            t.start();
        }
    }
}
