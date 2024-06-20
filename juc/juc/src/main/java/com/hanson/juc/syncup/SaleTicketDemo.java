package com.hanson.juc.syncup;

/**
 * @author hanson
 * @date 2024/6/19 0:06
 */
class Ticket {
    private int number = 50;
    Object lockobject = new Object();

    public void sale() {
        synchronized (lockobject) {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第: " + (number--) + "张票，剩余:" + number + " 张票");
            }
        }
    }
}
public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 55; i++){
                ticket.sale();
            }
        }, "a").start();

        new Thread(() -> {
            for (int i = 0; i < 55; i++){
                ticket.sale();
            }
        }, "b").start();

        new Thread(() -> {
            for (int i = 0; i < 55; i++){
                ticket.sale();
            }
        }, "c").start();
    }
}
