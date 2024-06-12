package com.hanson.juc.interrupt;

/**
 * @author hanson
 * @date 2024/6/6 17:09
 */
public class InterruptDemo4 {

    // 测试当前线程是否被中断(检查中断标志)，返回一个boolean并清除中断状态，
    // 第二次再调用时中断状态已经被清除，将返回一个false。
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName()+"\t" +Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"\t" +Thread.interrupted());
        System.out.println("----------------1");
        Thread.currentThread().interrupt();// 中断标志位设置为true
        System.out.println("----------------2");
        System.out.println(Thread.currentThread().getName()+"\t" +Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"\t" +Thread.interrupted());

        Thread.interrupted();// 静态方法

        System.out.println(Thread.currentThread().getName()+"\t" +Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"\t" +Thread.interrupted());

        Thread.currentThread().interrupt();// 实例方法

        System.out.println(Thread.currentThread().getName()+"\t" +Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"\t" +Thread.interrupted());
    }
}

