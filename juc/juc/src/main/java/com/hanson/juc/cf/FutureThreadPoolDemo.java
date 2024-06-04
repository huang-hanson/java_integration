package com.hanson.juc.cf;

import java.util.concurrent.*;

/**
 * @author hanson
 * @date 2024/5/30 18:13
 */
public class FutureThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 3个任务，目前只有多个异步线程来处理，请问耗时多少
        System.out.println("3个任务，目前只有多个异步线程来处理");

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();

        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1 over";
        });

        threadPool.submit(futureTask1);

        FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task2 over";
        });

        threadPool.submit(futureTask2);

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("------costTime: " + (endTime - startTime) + " 毫秒");

        System.out.println(Thread.currentThread().getName()+"\t ------end");
        threadPool.shutdown();


        m1();
    }

    private static void m1() {
        // 3个任务，目前只有一个main来处理，请问耗时多少
        System.out.println("3个任务，目前只有一个main来处理");

        long startTime = System.currentTimeMillis();
        // 暂停毫秒

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("------costTime: " + (endTime - startTime) + " 毫秒");

        System.out.println(Thread.currentThread().getName()+"\t ------end");
    }
}
