package com.hanson.juc.rwlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author hanson
 * @date 2024/6/20 8:27
 *
 * 锁降级:遵循获取写锁、再获取读锁→再释放写锁的次序，写锁能够降级成为读锁。
 *
 * 如果一个线程占有了写锁，在不释放写锁的情况下，它还能占有读锁，即写锁级为读锁。
 *
 * 读没有完成时候写锁无法获得锁，必须要等着读锁读完后才有机会写
 */
public class LockDownGradingDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        // 正常A，B两个线程
        // A
//        readLock.lock();
//        System.out.println("--------读取");
//        readLock.unlock();
//
//        // B
//        writeLock.lock();
//        System.out.println("--------写入");
//        writeLock.unlock();

        // 本例 only one 同一个线程
        writeLock.lock();
        System.out.println("--------写入");

        readLock.lock();
        System.out.println("--------读取");

        writeLock.unlock();

        readLock.unlock();
    }
}
