package com.hanson.juc.volatiles;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hanson
 * @date 2024/6/12 15:19
 */
class MyNumber{
    volatile int number;

    public void addPlusPlus(){
        number++;
    }

//    int number;
//
//    public synchronized void addPlusPlus(){
//        number++;
//    }
}

public class VolatileNoAtomicDemo {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
//                    myNumber.addPlusPlus();
                    try {
                        lock.lock();
                        myNumber.addPlusPlus();
                    } finally {
                        lock.unlock();
                    }
                }
            }).start();
        }

        //  暂停2秒钟
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(myNumber.number);
    }
}
