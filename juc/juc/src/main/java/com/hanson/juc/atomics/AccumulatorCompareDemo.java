package com.hanson.juc.atomics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author hanson
 * @date 2024/6/13 19:13
 */
class ClickNumber {
    int number = 0;

    public synchronized void clickBySynchronized() {
        number++;
    }

    AtomicLong atomicLong = new AtomicLong(0);

    public void clickByAtomicLong() {
        atomicLong.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();

    public void clickByLongAdder() {
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);

    public void clickByLongAccumulator() {
        longAccumulator.accumulate(1);
    }
}

/*
 * 需求:50个线程，每个线程100W次，总点赞数出来
 * */
public class AccumulatorCompareDemo {

    public static final int _1W = 10000;
    public static final int threadNumber = 50;

    public static void main(String[] args) throws InterruptedException {
        ClickNumber clickNumber = new ClickNumber();
        long startTime;
        long endTime;

        CountDownLatch countDownLatch1 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch2 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch3 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch4 = new CountDownLatch(threadNumber);

        // 1.synchronized
        startTime = System.currentTimeMillis();

        for (int i = 1; i <= threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickNumber.clickBySynchronized();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch1.await();
        endTime = System.currentTimeMillis();
        System.out.println("---------------costTime:" + (endTime - startTime) + " 毫秒" + "\t clickBySynchronized:" + clickNumber.number);


        // 2.AtomicLong
        startTime = System.currentTimeMillis();

        for (int i = 1; i <= threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickNumber.clickByAtomicLong();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println("---------------costTime:" + (endTime - startTime) + " 毫秒" + "\t clickByAtomicLong:" + clickNumber.atomicLong.get());

        // 3.LongAdder
        startTime = System.currentTimeMillis();

        for (int i = 1; i <= threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickNumber.clickByLongAdder();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println("---------------costTime:" + (endTime - startTime) + " 毫秒" + "\t clickByLongAdder:" + clickNumber.longAdder.sum());

        // 4.LongAccumulator
        startTime = System.currentTimeMillis();

        for (int i = 1; i <= threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickNumber.clickByLongAccumulator();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch4.await();
        endTime = System.currentTimeMillis();
        System.out.println("---------------costTime:" + (endTime - startTime) + " 毫秒" + "\t clickByLongAccumulator:" + clickNumber.longAccumulator.get());

        //---------------costTime:1478 毫秒	 clickBySynchronized:50000000
        //---------------costTime:965 毫秒	 clickByAtomicLong:50000000
        //---------------costTime:109 毫秒	 clickByLongAdder:50000000
        //---------------costTime:108 毫秒	 clickByLongAccumulator:50000000
    }
}
