package com.base.november.Three_zero;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ming
 * @date 2020/11/30 - 22:02
 * @describe
 */
public class Test {
    class Buffer {
        private final Lock lock;
        private final Condition notFull;
        private final Condition notEmpty;
        private int maxSize;
        private List<Date> storage;

        Buffer(int size) {
            //使用锁lock，并且创建两个condition，相当于两个阻塞队列
            lock = new ReentrantLock();
            notFull = lock.newCondition();
            notEmpty = lock.newCondition();
            maxSize = size;
            storage = new LinkedList<>();
        }

        public void put() {
            lock.lock();
            try {
                while (storage.size() == maxSize) {//如果队列满了
                    System.out.print(Thread.currentThread().getName() + ": wait \n");
                    notFull.await();//阻塞生产线程
                }
                storage.add(new Date());
                System.out.print(Thread.currentThread().getName() + ": put:" + storage.size() + "\n");
                Thread.sleep(100);
                notEmpty.signalAll();//唤醒消费线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void take() {
            lock.lock();
            try {
                while (storage.size() == 0) {//如果队列满了
                    System.out.print(Thread.currentThread().getName() + ": wait \n");
                    notEmpty.await();//阻塞消费线程
                }
                Date d = ((LinkedList<Date>) storage).poll();
                System.out.print(Thread.currentThread().getName() + ": take:" + storage.size() + "\n");
                Thread.sleep(100);
                notFull.signalAll();//唤醒生产线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    class Producer implements Runnable {
        private Buffer buffer;

        Producer(Buffer b) {
            buffer = b;
        }

        @Override
        public void run() {
            while (true) {
                buffer.put();
            }
        }
    }

    class Consumer implements Runnable {
        private Buffer buffer;

        Consumer(Buffer b) {
            buffer = b;
        }

        @Override
        public void run() {
            while (true) {
                buffer.take();
            }
        }
    }

    public static void main(String[] arg) {
        Test test = new Test();
        Buffer buffer = test.new Buffer(100);
        Producer producer = test.new Producer(buffer);
        Consumer consumer = test.new Consumer(buffer);
        for (int i = 0; i < 10; i++) {
            new Thread(producer, "producer-" + i).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(consumer, "consumer-" + i).start();
        }

    }
}
