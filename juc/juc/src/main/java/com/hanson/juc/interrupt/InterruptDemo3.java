package com.hanson.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author hanson
 * @date 2024/6/6 16:55
 *
 * 1.中断标志位默认为false
 * 2.t2对t1发出中断协商  t1.interrupt();
 * 3.  中断标志位为true： 正常情况 程序停止
 *     中断标志位为true  异常情况，.InterruptedException ，将会把中断状态清楚，中断标志位为false
 * 4。需要在catch块中，再次调用interrupt()方法将中断标志位设置为false;

 */
public class InterruptDemo3 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t " + "中断标志位：" + Thread.currentThread().isInterrupted() + " 程序停止");
                    break;
                }

                //sleep方法抛出InterruptedException后，中断标识也被清空置为false，如果没有在
                //catch方法中调用interrupt方法再次将中断标识置为true，这将导致无限循环了
                try {
                    Thread.sleep(200);
                }catch (InterruptedException e){
//                    Thread.currentThread().interrupt(); // 没有他，程序不会停正，中断个打断，看着sleep方法的源:
                    e.printStackTrace();
                }

                System.out.println("------hello InterruptDemo3");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            t1.interrupt();
        }, "t2").start();
    }
}
