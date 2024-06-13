package com.hanson.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author hanson
 * @date 2024/6/13 1:01
 */
public class ABADemo {

    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference stampedReference = new AtomicStampedReference(100, 1);

    public static void main(String[] args) {

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t" + "首次版本号：" + stamp);

            // 暂停500毫秒
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);

            System.out.println(Thread.currentThread().getName() + "\t" + "修改完成，当前值：" + stampedReference.getReference() + "\t" + "2次版本号：" + stampedReference.getStamp());

            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);

            System.out.println(Thread.currentThread().getName() + "\t" + "修改完成，当前值：" + stampedReference.getReference() + "\t" + "3次版本号：" + stampedReference.getStamp());

        }, "t3").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t" + "首次版本号：" + stamp);

            // 暂停1秒钟线程,等待上面的t3线程，发生了ABA问题
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean b = stampedReference.compareAndSet(100, 2022, stamp, stamp + 1);

            System.out.println(b + "\t" + stampedReference.getReference() + "\t" +  stampedReference.getStamp());


        }, "t4").start();
    }

    private static void abaHappen() {
        new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);

            // 暂停10毫秒
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicInteger.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            // 暂停10毫秒
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicInteger.compareAndSet(100, 2022) + "\t" + atomicInteger.get());
        }, "t2").start();
    }
}
