package com.hanson.juc.rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author hanson
 * @date 2024/6/20 7:58
 */
class MyResource { //资源类，模拟一个简单的缓存
    Map<String, String> map = new HashMap<>();

    //=====ReentrantLock 等价于=====synchronized，之前讲解过
    Lock lock = new ReentrantLock();
    //=====ReentrantReadwriteLock一体两面，读写互斥，读读共享
    ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void write(String key, String value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始写入");
            map.put(key, value);

            // 暂停毫秒
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "写入完成");
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void read(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始读取");

            String result = map.get(key);

//            try {
//                TimeUnit.MILLISECONDS.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println(Thread.currentThread().getName() + "读取完成，结果是：" + result);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource();

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.write(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.read(finalI + "");
            }, String.valueOf(i)).start();
        }

        // 暂停几秒钟
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.write(finalI + "", finalI + "");
            }, "新写锁线程->" + String.valueOf(i)).start();
        }

//        1开始写入
//        1写入完成
//        2开始写入
//        2写入完成
//        6开始写入
//        6写入完成
//        7开始写入
//        7写入完成
//        3开始写入
//        3写入完成
//        8开始写入
//        8写入完成
//        10开始写入
//        10写入完成
//        5开始写入
//        5写入完成
//        9开始写入
//        9写入完成
//        4开始写入
//        4写入完成
//        1开始读取
//        4开始读取
//        6开始读取
//        5开始读取
//        10开始读取
//        7开始读取
//        8开始读取
//        9开始读取
//        3开始读取
//        2开始读取
//        3读取完成，结果是：3
//        6读取完成，结果是：6
//        2读取完成，结果是：2
//        8读取完成，结果是：8
//        4读取完成，结果是：4
//        1读取完成，结果是：1
//        7读取完成，结果是：7
//        10读取完成，结果是：10
//        5读取完成，结果是：5
//        9读取完成，结果是：9
//        新写锁线程->3开始写入
//        新写锁线程->3写入完成
//        新写锁线程->1开始写入
//        新写锁线程->1写入完成
//        新写锁线程->2开始写入
//        新写锁线程->2写入完成
    }
}
