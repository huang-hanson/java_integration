package com.hanson.juc.cf;

import java.util.concurrent.*;

/**
 * @author hanson
 * @date 2024/6/3 21:59
 */
public class CompletableFutureUseDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {

            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "-----come in");
                System.out.println(Thread.currentThread().isDaemon());
                int result = ThreadLocalRandom.current().nextInt(10);
                //暂停几秒钟线程
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("--------1秒中后出结果：" + result);
                if (result > 5){
                    int i = 10/0;
                }
                return result;
            }, threadPool).whenComplete((v, e) -> {
                if (e == null) {
                    System.out.println("------计算完成，更新系统UpdateValue: " + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
                return null;
            });

            System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务了");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

        // 主线程不要立即结束，否则CompletableFuture默认使用的线程池会立即关闭：暂停3秒钟线程
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private static void future1() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-----come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--------1秒中后出结果：" + result);
            return result;
        });

        System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务了");

        System.out.println(completableFuture.get());
    }
}

/**
 * 无异常情况
 * pool-1-thread-1---come in
 * main先去完成其他任务
 * ----------1秒钟后出结果9
 * 计算完成 更新系统9
 */

/**
 * 有异常情况
 *pool-1-thread-1---come in
 * main先去完成其他任务
 * java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
 * 异常情况：java.lang.ArithmeticException: / by zero java.lang.ArithmeticException: / by zero
 */
