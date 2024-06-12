package com.hanson.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author hanson
 * @date 2024/6/5 18:24
 */
public class InterruptDemo {

    static volatile boolean isStop = false;

    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted被修改为true，程序停止");
                    break;
                }
                System.out.println("t1-------hello interrupted api");
            }
        }, "t1");

        t1.start();

        // 暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        // t2向t1发出协商，将t1的中断标志位设为true希望t1停下来
//        new Thread(() -> {
//           t1.interrupt();
//        }, "t2").start();

        // 也可以t1自己设置
        t1.interrupt();
    }

    private static void m2() {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "\t atomicBoolean被修改为true，程序停止");
                    break;
                }
                System.out.println("t1-------hello atomicBoolean");
            }

        }, "t1").start();

        // 暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }

    private static void m1() {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true，程序停止");
                    break;
                }
                System.out.println("t1-------hello volatile");
            }

        }, "t1").start();

        // 暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
}
/**
 * t1-----------hello atomicBoolean
 * t1-----------hello atomicBoolean
 * t1-----------hello atomicBoolean
 * t1-----------hello atomicBoolean
 * t1-----------hello atomicBoolean
 * t1-----------hello atomicBoolean
 * t1 atomicBoolean的值被改为true，t1程序停止
 */

