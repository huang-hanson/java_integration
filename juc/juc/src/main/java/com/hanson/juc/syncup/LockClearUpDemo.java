package com.hanson.juc.syncup;

/**
 * @author hanson
 * @date 2024/6/19 16:54
 */
public class LockClearUpDemo {

    static Object objectLock = new Object();

    public void m1() {
//        synchronized (objectLock){
//            System.out.println("---------------------hello LockClearUpDemo");
//        }

        // 锁消除问题，JIT编译器会无视他，synchronized(o),每次new出来的，不存在了，非正常的。
        Object o = new Object();

        synchronized (o) {
            System.out.println("---------------------hello LockClearUpDemo" + "\t" + o.hashCode() + "\t" + objectLock.hashCode());
        }

    }

    public static void main(String[] args) {
        LockClearUpDemo lockClearUpDemo = new LockClearUpDemo();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                lockClearUpDemo.m1();
            }, String.valueOf(i)).start();
        }
    }
}
