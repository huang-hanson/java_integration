package com.hanson.juc.cf;

import java.util.concurrent.*;

/**
 * @author hanson
 * @date 2024/6/3 21:44
 */
public class CompletableFutureBuildDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName());
//            //暂停几秒钟线程
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, threadPool);
//
//        System.out.println(completableFuture.get());        //null  没有返回值
        //ForkJoinPool.commonPool-worker-9

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "hello supplyAsync";
        }, threadPool);
        System.out.println(completableFuture.get());// hello supplyAsync  且用了自己指定的线程池


        threadPool.shutdown();
    }
}
