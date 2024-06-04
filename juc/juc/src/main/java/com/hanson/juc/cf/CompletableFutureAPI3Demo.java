package com.hanson.juc.cf;

import java.util.concurrent.CompletableFuture;

/**
 * @author hanson
 * @date 2024/6/4 12:42
 */
public class CompletableFutureAPI3Demo {
    public static void main(String[] args) {
//        CompletableFuture.supplyAsync(() -> {
//            return 1;
//        }).thenApply(f -> {
//            return f + 2;
//        }).thenApply(f -> {
//            return f + 3;
//        }).thenAccept(r -> {
//            System.out.println(r);//6
//        });

        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {}).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(r -> System.out.println(r)).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(r -> r + "resultB").join());
        /*
        * null
        * resultA
        * null
        * resultAresultB
        * */
    }
}
