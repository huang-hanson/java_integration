package com.hanson.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author hanson
 * @date 2024/6/12 19:37
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t" + "---come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t" + "---task over,unLock...");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        },"A").start();

        // 暂停500ms
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.lock();

            spinLockDemo.unlock();
        },"B").start();

        //A	---come in
        //B	---come in
        //A	---task over,unLock...
        //B	---task over,unLock...
    }
}
