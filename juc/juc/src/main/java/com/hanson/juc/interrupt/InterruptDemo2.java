package com.hanson.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author hanson
 * @date 2024/6/6 15:55
 *  * 执行interrupt方法将t1标志位设置为true后，t1没有中断，仍然完成了任务后再结束
 *  * 在2000毫秒后，t1已经结束称为不活动线程，设置状态为没有任何影响
 */
public class InterruptDemo2 {
    public static void main(String[] args) {
        // 实例方法interrupt()仅仅是设置线程的中断状态位设置为true，不会停止线程
        Thread t1 = new Thread(() -> {
            for (int i = 1; i < 300; i++) {
                System.out.println("-------: " + i);
            }
            System.out.println("t1线程调用interrupt()后的中断标志02：" + Thread.currentThread().isInterrupted());
        }, "t1");

        t1.start();

        System.out.println("t1线程默认的中断标志：" + t1.isInterrupted());//false

        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        t1.interrupt();

        System.out.println("t1线程调用interrupt()后的中断标志01：" + t1.isInterrupted());//true

        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("t1线程调用interrupt()后的中断标志03：" + t1.isInterrupted());//true
    }
}
