package com.hanson.rpc;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author hanson
 * @date 2024/3/13 16:20
 */
public class CalculatorServer {

    public static void main(String[] args) {
        try {
            CalculatorService calculatorService = new CalculatorServiceImpl();
            Registry registry = LocateRegistry.createRegistry(8081);
            registry.rebind("CalculatorService", calculatorService);
            System.out.println("服务器运行中...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
