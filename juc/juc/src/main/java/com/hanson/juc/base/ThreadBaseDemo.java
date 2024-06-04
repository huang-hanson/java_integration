package com.hanson.juc.base;

/**
 * @author hanson
 * @date 2024/5/30 16:40
 */
public class ThreadBaseDemo {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {

        }, "t1");
        t1.start();

        Object o = new Object();

        new Thread(() -> {
            synchronized (o) {

            }
        }, "t2").start();

    }
}
