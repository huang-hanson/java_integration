package com.hanson.rpc;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.CompletableFuture;

/**
 * @author hanson
 * @date 2024/3/13 16:51
 */
public class CalculatorAsyncClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8081);
            CalculatorService calculatorService = (CalculatorService) registry.lookup("CalculatorService");

            // 异步调用 a + b
            CompletableFuture<Integer> addFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    // 模拟远程调用耗时
                    Thread.sleep(10000);
                    return calculatorService.add(10, 5);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            // 注册异步回调，当异步调用完成时执行
            addFuture.thenAcceptAsync(resultAdd -> {
                System.out.println("Result of addition: " + resultAdd);
            });

            // 主线程继续执行其他操作
            int a = 10;
            int b = 5;
            int resultSubtract = a - b;
            System.out.println("Result of subtraction: " + resultSubtract);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
