package com.hanson.juc.LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hanson
 * @date 2024/6/11 18:17
 */
public class LockSupportDemo {
    public static void main(String[] args) {

        /**
         * t2发出通知
         * t1线程开始执行
         * t1线程被唤醒
         */
        Thread t1 = new Thread(() -> {
            // 暂停几秒钟
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "线程开始执行");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "线程被唤醒");
        }, "t1");
        t1.start();

        // 暂停几秒钟
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "发出通知");
        }, "t2").start();
    }

    private static void lockAwaitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "线程开始执行");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "线程被唤醒");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "t1").start();

        // 暂停几秒钟
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "发出通知");
            }finally {
                lock.unlock();
            }
        },"t2").start();
    }

    private static void syncWaitNotify() {
        Object objectLock = new Object();
        /*
        * t1线程开始执行
        * t1线程开始等待
        * t2发出通知
        * t1线程被唤醒
        * */
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "线程开始执行");
                try {
                    System.out.println(Thread.currentThread().getName() + "线程开始等待");
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "线程被唤醒");
            }
        }, "t1").start();

        // 暂停几秒钟
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (objectLock){
                objectLock.notify();
                System.out.println(Thread.currentThread().getName()+"发出通知");
            }
        },"t2").start();
    }
}
