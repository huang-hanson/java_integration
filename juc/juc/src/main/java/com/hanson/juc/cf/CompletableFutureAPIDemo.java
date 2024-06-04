package com.hanson.juc.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author hanson
 * @date 2024/6/4 12:20
 */
public class CompletableFutureAPIDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "abc";
        });

//        System.out.println(completableFuture.get());  // 不见不散
//        System.out.println(completableFuture.get(2L,TimeUnit.SECONDS)); // 过时不候
//        System.out.println(completableFuture.join());
        //暂停几秒钟
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // System.out.println(completableFuture.getNow("xxx"));// 调用getNow方法，如果completableFuture未执行完，则返回xxx，如果已经执行完，则返回abc

        System.out.println(completableFuture.complete("completeValue" + "\t" + completableFuture.get()));
    }
}
