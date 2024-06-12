package com.hanson.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * @author hanson
 * @date 2024/6/5 17:38
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        final Object objectA = new Object();
        final Object objectB = new Object();

        new Thread(() -> {
            synchronized (objectA) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有A锁，希望获得B锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (objectB) {
                        System.out.println(Thread.currentThread().getName() + "\t 成功获得B锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objectB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有B锁，希望获得A锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (objectA) {
                        System.out.println(Thread.currentThread().getName() + "\t 成功获得A锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}
