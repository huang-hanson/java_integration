package com.hanson.juc.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author hanson
 * @date 2024/6/4 12:32
 */
public class CompletableFutureAPI2Demo {
    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1;
//        }, threadPool).thenApply(f -> { // 这个是会处理异常
        }, threadPool).handle((f, e) -> { // 这个不是处理异常，遇到一场继续往下执行
            int i = 10 / 0; // 在handle中可以继续执行，带着异常参数往下执行
            System.out.println("222");
            return f + 2;
//        }).thenApply(f -> {
        }).handle((f, e) -> {
            System.out.println("333");
            return f + 2;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----计算结果：" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + "-----主线程先去忙其他任务");

        threadPool.shutdown();
    }
}
