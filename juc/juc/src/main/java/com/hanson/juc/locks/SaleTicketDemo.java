package com.hanson.juc.locks;

import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    // 资源类，模拟三个售票员卖完50张票
    private int number = 50;
    ReentrantLock lock = new ReentrantLock(true);// 什么都没写，表示非公平锁

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下：" + number);
            }
        }finally {
            lock.unlock();
        }
    }
}

/**
 * @author hanson
 * @date 2024/6/5 15:13
 */
public class SaleTicketDemo {
    public static void main(String[] args) {
        // 一切程序的入口

        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        },"a").start();

        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        },"b").start();

        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        },"c").start();
    }
}
