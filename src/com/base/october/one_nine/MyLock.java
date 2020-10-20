package com.base.october.one_nine;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ming
 * @date 2020/10/19 - 16:22
 * @describe
 */
public class MyLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         *         * 尝试获取锁的方法
         *         * 需要自己实现的流程方法
         *         * @param arg
         *         * @return
         *
         */
        @Override
        protected boolean tryAcquire(int arg) {
            //CAS比较内存中的原始值为0，则修改为传入的状态值1，当前线程获取到锁
            if (compareAndSetState(0, arg)) {
                setExclusiveOwnerThread(Thread.currentThread());//当前线程得到了锁，则将当前得到锁的线程设置为独占线程
                return true;
            }
            return false;
        }

        /**
         * 释放锁的方法，需要实现
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {

            if (getState() == 0) {//判断状态是否为0，为0则直接抛出不支持操作的异常，增强健壮性的代码

                throw new UnsupportedOperationException();

            }

            setExclusiveOwnerThread(null);//将当前独占线程设置为null

            setState(0);//将当前标志锁状态的值设置为0，表示锁已经释放

            return true;
        }

        /**
         * 是否同步独占，true--已被独占，false--未被独占
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
        Condition newCondition() {
            return new ConditionObject();//AQS已经实现Condition,此处只需要直接实例化并使用AQS中的实现即可
        }
    }



    private Sync sync = new Sync();
    @Override
    public void lock() {
        sync.acquire(1); //获取锁
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);//获取锁，允许获取过程中有中断
    }


    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));//获取锁，有超时机制
    }

    @Override
    public void unlock() {
        sync.release(1);//释放锁
    }


    @Override
    public Condition newCondition() {
        return sync.newCondition();//获取AQS中的Condition实例，用于等待、唤醒操作
    }
}