package com.hanson.juc.atomics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author hanson
 * @date 2024/6/13 17:16
 */
public class AtomicMarkableReferenceDemo {

    static AtomicMarkableReference atomicMarkableReference = new AtomicMarkableReference(100, false);

    public static void main(String[] args) {
        new Thread(() -> {
            boolean marked = atomicMarkableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t" + "默认标志：" + marked);
            // 暂停1秒钟线程,等待后面的T2线程和我拿到一样的模式flag标识，都是false
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicMarkableReference.compareAndSet(100, 1000, marked, !marked);

        }, "t1").start();

        new Thread(() -> {
            boolean marked = atomicMarkableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t" + "默认标志：" + marked);
            // 暂停1秒钟线程,等待后面的T2线程和我拿到一样的模式flag标识，都是false
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean b = atomicMarkableReference.compareAndSet(100, 2000, marked, !marked);

            System.out.println(Thread.currentThread().getName() + "\t" + "t2线程CASResult：" + b);
            System.out.println(Thread.currentThread().getName() + "\t" + atomicMarkableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + "\t" + atomicMarkableReference.getReference());
        }, "t2").start();

        //t1	默认标志：false
        //t2	默认标志：false
        //t2	t2线程CASResult：false
        //t2	true
        //t2	1000
    }
}
