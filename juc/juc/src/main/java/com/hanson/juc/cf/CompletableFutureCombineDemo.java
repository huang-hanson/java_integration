package com.hanson.juc.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author hanson
 * @date 2024/6/4 14:27
 */
public class CompletableFutureCombineDemo {

    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----启动");
            // 暂停几秒钟
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 10;
        });

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----启动");
            // 暂停几秒钟
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 20;
        });

        CompletableFuture<Integer> result = completableFuture1.thenCombine(completableFuture2, (x, y) -> {
            System.out.println("-------------开始两个结果合并");
            return x + y;
        });

        System.out.println(result.join());

        //ForkJoinPool.commonPool-worker-9	 ----启动
        //ForkJoinPool.commonPool-worker-2	 ----启动
        //-------------开始两个结果合并
        //30
    }
}
