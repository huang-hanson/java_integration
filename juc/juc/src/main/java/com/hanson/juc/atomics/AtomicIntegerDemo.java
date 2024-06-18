package com.hanson.juc.atomics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hanson
 * @date 2024/6/13 14:06
 */
class MyNumber {
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlusPlus() {
        atomicInteger.getAndIncrement();
    }
}

public class AtomicIntegerDemo {

    public static final int SIZE = 50;

    public static void main(String[] args) throws InterruptedException{
        MyNumber myNumber = new MyNumber();

        CountDownLatch countDownLatch = new CountDownLatch(SIZE);

        for (int i = 1; i <= SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 1000; j++) {
                        myNumber.addPlusPlus();
                    }
                }finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }

        // 等待上面50个线程执行完后，再去获取最终值

//        try {
//            TimeUnit.SECONDS.sleep(2);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t" + "result:" + myNumber.atomicInteger.get());
    }
}
