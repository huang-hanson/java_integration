package com.hanson.juc.base;

import java.util.concurrent.TimeUnit;

/**
 * @author hanson
 * @date 2024/5/30 17:35
 */
public class DaemoDemo {
    public static void main(String[] args) {// 一切方法的入口

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 开始运行， " +
                    (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            // while时死循环，如果t1是用户线程，他会一直转，一直运行，如果t1设置成守护线程，他在main线程结束后就会消失
            while (true) {

            }
        }, "t1");

        // 将t1线程设置为守护线程
        // setDaemon(true)方法必须在start()之前设置，否则报IllegalThreadStateException异常
        t1.setDaemon(true);
        t1.start();

        // 暂停几秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t ------end 主线程");
    }
}
