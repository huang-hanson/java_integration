package com.hanson.juc.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author hanson
 * @date 2024/6/4 13:08
 */
public class CompletableFutureAPI4Demo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try {
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1号任务：" + "\t" + Thread.currentThread().getName());
                return "abcd";
//            }, threadPool).thenRun(() -> {
            }, threadPool).thenRunAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2号任务：" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
//            }).thenRunAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3号任务：" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
//            }).thenRunAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("4号任务：" + "\t" + Thread.currentThread().getName());
            });

            System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
