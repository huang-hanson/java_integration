package com.hanson.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hanson
 * @date 2024/6/12 18:06
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());
    }
}
