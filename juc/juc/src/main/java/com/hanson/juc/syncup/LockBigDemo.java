package com.hanson.juc.syncup;

/**
 * @author hanson
 * @date 2024/6/19 17:25
 */
public class LockBigDemo {

    static Object objectLock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println("111111");
            }

            synchronized (objectLock) {
                System.out.println("222222");
            }

            synchronized (objectLock) {
                System.out.println("333333");
            }

            synchronized (objectLock) {
                System.out.println("444444");
            }

            //底层JIT的锁粗化优化
            synchronized (objectLock) {
                System.out.println("111111");
                System.out.println("222222");
                System.out.println("333333");
                System.out.println("444444");
            }
        }, "t1").start();
    }
}
