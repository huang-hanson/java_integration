package com.hanson.juc.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hanson
 * @date 2024/6/19 18:02
 */
public class AQSDemo {
    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();// 非公平锁

        //ABC三个顾客，去银行办理业务，A先到,此时窗口空无一人，他优先获得办理窗口的机会，办理业务。
        //A 耗时严重，估计长期占有窗口
        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("----------come in A");
                // 暂停20mi线程
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                reentrantLock.unlock();
            }
        }, "A").start();

        // B是第2个顾客，B一看到受理窗口被A占用，只能去候客区等待，进入AQS队列，等待着A办理完成，尝试去抢占受理窗口。
        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("----------come in B");
            } finally {
                reentrantLock.unlock();
            }
        }, "B").start();

        // C是第3个顾客，C一看到受理窗口被A占用，只能去候客区等待，进入AQS以列，等待着A办理完成，尝试去抢占受理窗口,前面是B顾客，FIFO
        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("----------come in C");
            } finally {
                reentrantLock.unlock();
            }
        }, "C").start();

        // 后续顾客DEFG。。。。以此类推
        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("----------come in D");
            } finally {
                reentrantLock.unlock();
            }
        }, "D").start();
    }
}
